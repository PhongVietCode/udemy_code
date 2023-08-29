package com.example.kotlin_learn.ViewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_learn.R

class ViewPagerActivity : AppCompatActivity() {
    lateinit var myAdapter: PageAdapter
    private val arrayList = ArrayList<String>()
    lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        // 0 - Init item
        viewPager = findViewById(R.id.viewPager)
        myAdapter = PageAdapter(this)
        // 1 - Setting the adapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.adapter = myAdapter

    }
}