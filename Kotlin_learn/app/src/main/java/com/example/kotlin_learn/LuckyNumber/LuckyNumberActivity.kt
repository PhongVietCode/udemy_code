package com.example.kotlin_learn.LuckyNumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.kotlin_learn.R
import kotlin.random.Random

class LuckyNumberActivity : AppCompatActivity() {
    lateinit var viewModel: LuckyNumberViewModel
    lateinit var factory: LuckyViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lucky_number2)
        // init View Item
        val text1 : TextView = findViewById(R.id.txtRes)
        val text2:TextView = findViewById(R.id.txtLuckyNumber)
        val shareBtn: Button = findViewById(R.id.btnShare)
        val btnAdd :Button = findViewById(R.id.btnAdd)

        factory = LuckyViewModelFactory(10)
        viewModel = ViewModelProvider(this,factory).get(LuckyNumberViewModel::class.java)

        var username = ReceiveMessage()

        var number = viewModel.getCurrentCounter()
        text2.text = number.toString()

        shareBtn.setOnClickListener(){
            shareData(username,number )
        }
        btnAdd.setOnClickListener(){
            viewModel.updateCounter()
            text2.text = viewModel.number.value.toString()
        }
        viewModel.number.observe(this,
            Observer { text2.text = it.toString()
            })
    }
    fun ReceiveMessage(): String {
        // get transfer data
        val bundle: Bundle? = intent.extras
        return bundle?.get("name").toString()
    }
    fun generateNumber(): Int {
        return Random.nextInt(1000)
    }
    fun shareData(username:String, num:Int){
        var i = Intent(Intent.ACTION_SEND)
        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_SUBJECT,""+ username+ " is lucky to day")
        i.putExtra(Intent.EXTRA_TEXT, "Lucky number is $num")
        startActivity(i)
    }
}