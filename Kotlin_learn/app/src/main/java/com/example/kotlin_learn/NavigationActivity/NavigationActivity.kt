package com.example.kotlin_learn.NavigationActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kotlin_learn.Fragments.FirstFragment
import com.example.kotlin_learn.Fragments.HomeFragment
import com.example.kotlin_learn.Fragments.SecondFragment
import com.example.kotlin_learn.R
import com.google.android.material.navigation.NavigationView

class NavigationActivity : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        // 1 - Init DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // 2 - register listener for drawLayout
        toggle = ActionBarDrawerToggle(
            this@NavigationActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 3 - Support action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Navigation Activity"

        // 4 - functionality for the nav items
        replaceFragment(HomeFragment(), "Home")

        val navViews: NavigationView = findViewById(R.id.naviView)
        navViews.setNavigationItemSelectedListener {
            it.isChecked =true
            when(it.itemId){
                R.id.home ->{
                    replaceFragment(HomeFragment(), it.title.toString())
                }
                R.id.message -> {
                    replaceFragment(FirstFragment(), it.title.toString())
                }
                R.id.setting -> {
                replaceFragment(SecondFragment(),it.title.toString())
                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutNavigation, fragment)
            .commit()
        drawerLayout.closeDrawers()
        supportActionBar?.title = title

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}