package com.example.cubosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cubosapp.R
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.widget.Adapter
import android.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosapp.adapter.ListItemAdapter
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.SearchInterfaces
import com.example.cubosapp.presenter.SearchPresenter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchInterfaces.View {

    private var isLoadingData = false
    private var presenter = SearchPresenter(this)
    private var lastVisibleItemPosition: Int = 0
    private var listItemAdapter : ListItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        handleIntent(intent)
    }

    override fun onNewIntent( intent :Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search_menu).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo( componentName))
            isIconifiedByDefault = false
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            supportActionBar?.title = query
            presenter.searchMovie(query)
            isLoadingData = true
        }
    }

    override fun setViewData(movies: List<MovieCard>) {
        val layoutManager = GridLayoutManager (this.baseContext, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        searchMoviesContainer.layoutManager = layoutManager
        listItemAdapter = ListItemAdapter(ArrayList(movies), {movieCard : MovieCard -> onItemClick(movieCard) })
        searchMoviesContainer.adapter = listItemAdapter
        searchMoviesContainer.itemAnimator = DefaultItemAnimator()
        isLoadingData = false
        lastVisibleItemPosition = 0

        searchMoviesContainer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!presenter.getLimitData()){
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    if (!isLoadingData && totalItemCount <= (lastVisibleItemPosition + 10)) {
                        isLoadingData = true
                        presenter.updateData()
                    }
                    lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                }
            }
        })
    }

    override fun updateViewData(movies: List<MovieCard>) {
        isLoadingData = false

        listItemAdapter?.addAll(movies)
        listItemAdapter?.notifyDataSetChanged()
    }

    fun onItemClick(movie : MovieCard ){
        val movieDetail = Intent(this.baseContext, MovieDetailActivity::class.java)
        movieDetail.putExtra("MovieId" , movie.id)
        movieDetail.putExtra("MovieTitle", movie.title)
        startActivity(movieDetail)
    }
}
