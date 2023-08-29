package com.example.kotlin_learn.TabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_learn.Fragments.FirstFragment
import com.example.kotlin_learn.Fragments.HomeFragment
import com.example.kotlin_learn.Fragments.SecondFragment

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
       return 3
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