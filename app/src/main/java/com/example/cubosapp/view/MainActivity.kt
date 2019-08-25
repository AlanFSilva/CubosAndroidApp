package com.example.cubosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.R
import com.example.cubosapp.adapter.TabsFragmentAdapter
import com.example.cubosapp.data.Genre
import com.example.cubosapp.presenter.MainPresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View {

    private var presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun initView(genres: List<Genre>){
        val adapter = TabsFragmentAdapter( supportFragmentManager)

        genres.map {it
            tabs_container.addTab(tabs_container.newTab().setText(it.name))
            adapter.addFragment(MoviesListView(presenter.getTabData(it.id)),it.name)
        }
        tabs_container!!.tabGravity = TabLayout.GRAVITY_FILL
        moviePagesContainer.adapter = adapter

        moviePagesContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs_container))
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

    override fun updateViewData(){

    }

    override fun getSelectedItem(){

    }

    override fun searchMovie(){

    }
}
