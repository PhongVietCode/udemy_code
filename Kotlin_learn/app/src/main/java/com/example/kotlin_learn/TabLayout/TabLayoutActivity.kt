package com.example.kotlin_learn.TabLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_learn.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : AppCompatActivity() {
    val tabArray  = arrayOf("Home", "Frag1","Frag2")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Navi_Theme)
        setContentView(R.layout.activity_tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPagerTab)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adapter = TabAdapter(
            supportFragmentManager,
            lifecycle
        )
        //link view pager with adapter
        viewPager.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        //link viewPager with tabLayout
        TabLayoutMediator(tabLayout,viewPager){
            tab, position -> tab.text = tabArray[position]
        }.attach()
    }
}