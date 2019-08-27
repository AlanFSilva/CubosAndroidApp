package com.example.cubosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.R
import com.example.cubosapp.adapter.TabsFragmentAdapter
import com.example.cubosapp.data.Genre
import com.example.cubosapp.presenter.MainPresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View {

    private var presenter = MainPresenter(this)
    private var pagerAdapter  = TabsFragmentAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.initializeView()
    }

    override fun onResume() {
        super.onResume()
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

    override fun setViewData(genre: Genre?){
        if (genre != null) {
            pagerAdapter.addFragment(MoviesListView(presenter.getTabData(genre.id)),genre.name)
            moviePagesContainer.adapter = pagerAdapter
        }
    }

    override fun updateViewData(genre: Genre?){

    }

    override fun getSelectedItem(){

    }

    override fun searchMovie(){

    }
}
