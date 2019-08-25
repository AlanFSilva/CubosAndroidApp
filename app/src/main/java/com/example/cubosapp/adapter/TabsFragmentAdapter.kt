package com.example.cubosapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){

    private val _fragments: ArrayList<Fragment> = ArrayList()
    private val _titles: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment? {
       return _fragments.get(position)
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return _fragments.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        _fragments.add(fragment)
        _titles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return _titles.get(position)
    }


}