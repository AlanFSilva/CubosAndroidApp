package com.example.cubosapp.view

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.R
import com.example.cubosapp.adapter.TabsFragmentAdapter
import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.presenter.MainPresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View {

    private var presenter = MainPresenter(this)
    private var pagerAdapter  = TabsFragmentAdapter(supportFragmentManager)
    private var isLoadingData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setTitle(R.string.movies_title)
        presenter.initializeView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search_menu).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo( ComponentName(this.context,SearchActivity::class.java)))
            setIconifiedByDefault(false)
        }
        return true
    }

    override fun onSearchRequested(): Boolean {
        startSearch(null, false, null, true)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun initView(genres: List<Genre>){
        genres.map {it
            tabs_container.addTab(tabs_container.newTab().setText(it.name))
        }
        moviePagesContainer.adapter = pagerAdapter
        moviePagesContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs_container))

        tabs_container!!.tabGravity = TabLayout.GRAVITY_FILL
        tabs_container.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                moviePagesContainer.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun setViewData(genre: Genre?, movies: List<MovieCard>?){
        if (movies != null && genre != null) {
            pagerAdapter.addFragment(MoviesListView(ArrayList(movies), genre.id, ::onScrollStateChanged),genre.name, genre.id)
            moviePagesContainer.adapter = pagerAdapter
        }
    }

    override fun updateViewData(id: Int, movies: List<MovieCard>?){
        if (movies != null) {
            pagerAdapter.updateFragmentData(id, movies)
            isLoadingData = false
        }
    }

    fun onScrollStateChanged( genre: Int) {
        if(!presenter.getLimitData(genre) && !isLoadingData){
            isLoadingData = true
            presenter.getDataValue(genre)
        }
    }

}
