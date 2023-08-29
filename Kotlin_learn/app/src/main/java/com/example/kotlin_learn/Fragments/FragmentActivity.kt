package com.example.kotlin_learn.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin_learn.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val btnChange: Button = findViewById(R.id.btnChange)
        val frag1 :Fragment = FirstFragment()
        val frag2:Fragment =SecondFragment()
        var change:Boolean = true
        btnChange.setOnClickListener(){
            var fragment :Fragment? = null
            fragment = if(change){
                frag1
            }else{
                frag2
            }
            change =!change
            val ftrans : FragmentTransaction =
                supportFragmentManager.beginTransaction()
            ftrans.replace(R.id.fragmenContainer, fragment)
            .commit()
        }
    }
}