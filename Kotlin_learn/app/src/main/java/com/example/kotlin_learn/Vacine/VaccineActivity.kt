package com.example.kotlin_learn.Vacine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.kotlin_learn.R

class VaccineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine)

        val adapter = VaccineAdapter(getDataSource())
        val recyclerView = findViewById<RecyclerView>(R.id.vaccineRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
    fun getDataSource():ArrayList<VaccineModel>{
        val results = ArrayList<VaccineModel>()
        results.add(VaccineModel("Make an appointment",R.drawable.hd_wallpaper_new_fantasy_pink_blue_gradient_simple_colors))
        results.add(VaccineModel("Call the doctor",R.drawable.hd_wallpaper_new_fantasy_pink_blue_gradient_simple_colors))
        results.add(VaccineModel("Healthy for tip",R.drawable.hd_wallpaper_new_fantasy_pink_blue_gradient_simple_colors))
        return results
    }
}