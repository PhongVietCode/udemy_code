package com.example.kotlin_learn.ViewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_learn.Fragments.FirstFragment
import com.example.kotlin_learn.Fragments.HomeFragment
import com.example.kotlin_learn.Fragments.SecondFragment
import com.example.kotlin_learn.MainActivity

class PageAdapter (mainActivity:ViewPagerActivity): FragmentStateAdapter(mainActivity) {
    private val fragment_size =3

    override fun getItemCount(): Int {
        return fragment_size
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return HomeFragment()
            }
            1 ->{
                return FirstFragment()
            }
            2 ->{
                return SecondFragment()
            }
        }
        return HomeFragment()
    }


}