package com.example.cubosapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.view.MoviesListView

class TabsFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){

    private val _fragments: ArrayList<MoviesListView> = ArrayList()
    private val _titles: ArrayList<String> = ArrayList()
    private val _ids: ArrayList<Int> = ArrayList()

    override fun getItem(position: Int): MoviesListView? {
       return _fragments.get(position)
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return _fragments.size
    }

    fun addFragment(fragment: MoviesListView, title: String, id: Int) {
        _fragments.add(fragment)
        _titles.add(title)
        _ids.add(id)
    }

    fun updateFragmentData(id: Int, data: List<MovieCard>){
        val index = _ids.indexOf(id)
        _fragments[index].updateData(data)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return _titles.get(position)
    }


}