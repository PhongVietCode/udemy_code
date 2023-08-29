package com.example.kotlin_learn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.kotlin_learn.Coroutine.CoroutineActivity
import com.example.kotlin_learn.Firebase.FirebaseActivity
import com.example.kotlin_learn.Fragments.FragmentActivity
import com.example.kotlin_learn.LuckyNumber.LuckyNumber
import com.example.kotlin_learn.LuckyNumber.LuckyNumberActivity
import com.example.kotlin_learn.Navigation.NaviActivity
import com.example.kotlin_learn.NavigationActivity.NavigationActivity
import com.example.kotlin_learn.Retrofit.RetrofitActivity
import com.example.kotlin_learn.TabLayout.TabLayoutActivity
import com.example.kotlin_learn.Vacine.VaccineActivity
import com.example.kotlin_learn.ViewPager.ViewPagerActivity
import com.example.kotlin_learn.WorldCup.WorldCupActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    @SuppressLint("IntentWithNullActionLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fabLucky:FloatingActionButton = findViewById(R.id.fabLucky)
        fabLucky.setOnClickListener(){
            startActivity(Intent(this, LuckyNumber::class.java))
        }
        val fabWorldCup :FloatingActionButton = findViewById(R.id.fabWorldCup)
        fabWorldCup.setOnClickListener(){
            startActivity(Intent(this@MainActivity,WorldCupActivity::class.java))
        }
        val fabVaccine = findViewById<FloatingActionButton>(R.id.fabVaccine)
        fabVaccine.setOnClickListener(){
            startActivity(Intent(this@MainActivity, VaccineActivity::class.java))
        }
        val fabFragemt = findViewById<FloatingActionButton>(R.id.fabFragment)
        fabFragemt.setOnClickListener(){
            startActivity(Intent(this@MainActivity, FragmentActivity::class.java))
        }
        val fabNav = findViewById<FloatingActionButton>(R.id.fabNav)
        fabNav.setOnClickListener(){
            startActivity(Intent(this@MainActivity, NavigationActivity::class.java))
        }
        val fabPager = findViewById<FloatingActionButton>(R.id.fabPager )
        fabPager.setOnClickListener(){
            startActivity(Intent(this@MainActivity, ViewPagerActivity::class.java))
        }
        val fabTab = findViewById<FloatingActionButton>(R.id.fabTab )
        fabTab.setOnClickListener(){
            startActivity(Intent(this@MainActivity, TabLayoutActivity::class.java))
        }
        val fabContact = findViewById<FloatingActionButton>(R.id.fabContact )
        fabContact.setOnClickListener(){
//            startActivity(Intent(this@MainActivity, ContactApp::class.java))
            Toast.makeText(this@MainActivity,"Coming soon!",Toast.LENGTH_SHORT).show()
        }
        val fabNavi = findViewById<FloatingActionButton>(R.id.fabNavi )
        fabNavi.setOnClickListener(){
            startActivity(Intent(this@MainActivity, NaviActivity::class.java))
        }
        val fabRoutine = findViewById<FloatingActionButton>(R.id.fabRoutine)
        fabRoutine.setOnClickListener(){
            startActivity(Intent(this@MainActivity, CoroutineActivity::class.java))
        }
        val fabRetrofit = findViewById<FloatingActionButton>(R.id.fabRetrofit)
        fabRetrofit.setOnClickListener(){
            startActivity(Intent(this@MainActivity, RetrofitActivity::class.java))
        }
        val fabFirebase = findViewById<FloatingActionButton>(R.id.fabFirebase)
        fabFirebase.setOnClickListener(){
            startActivity(Intent(this@MainActivity, FirebaseActivity::class.java))
        }
    }
}