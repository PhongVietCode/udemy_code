package com.example.kotlin_learn.Firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_learn.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FirebaseActivity : AppCompatActivity() {
    val tabArray = arrayOf("Real time", "Fire store")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_acivity)
        val viewPager :ViewPager2= findViewById(R.id.viewPagerFirebase)
        val tabLayout :TabLayout= findViewById(R.id.firebaseTablayout)

        val tabAdapter = TabAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = tabAdapter

        // link viewPager with tabLayout
        TabLayoutMediator(tabLayout,viewPager){
            tab, position -> tab.text =  tabArray[position]
        }.attach()
    }
}