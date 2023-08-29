package com.example.kotlin_learn.WorldCup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.kotlin_learn.R

class WorldCupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world_cup)
        // Init item
        val listItem : ListView = findViewById(R.id.listCountry)

        val adapter = CountryAdapter(this@WorldCupActivity,getDataSource())
        listItem.adapter = adapter
        adapter.notifyDataSetChanged()
        supportActionBar?.setTitle("ListView")
    }
    fun getDataSource():ArrayList<CountryModel>{
        val result = ArrayList<CountryModel>()
        result.add(CountryModel(R.drawable.ic_launcher_foreground,"VietNam","100"))
        result.add(CountryModel(R.drawable.ic_launcher_foreground,"Brazil","20"))
        result.add(CountryModel(R.drawable.ic_launcher_foreground,"USA","23"))
        result.add(CountryModel(R.drawable.ic_launcher_foreground,"Germany","32"))
        return result
    }
}