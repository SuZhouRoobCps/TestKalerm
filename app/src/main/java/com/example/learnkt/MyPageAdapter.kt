package com.example.learnkt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPageAdapter(fm: FragmentManager,
                    behavior: Int,
                    val fragmentList:ArrayList<Fragment>)
    : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
       return fragmentList.size
    }
}