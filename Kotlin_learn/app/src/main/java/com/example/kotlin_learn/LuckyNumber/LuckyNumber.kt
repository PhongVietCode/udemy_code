package com.example.kotlin_learn.LuckyNumber

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.kotlin_learn.Person
import com.example.kotlin_learn.R
import com.example.kotlin_learn.Utils
import com.example.kotlin_learn.databinding.ActivityLuckyNumberBinding

class LuckyNumber : AppCompatActivity() {
    // initialize data binding
    private lateinit var binding: ActivityLuckyNumberBinding

    @SuppressLint("IntentWithNullActionLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // announce that this layout use data binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_lucky_number)

        var user1 :Person = Person("Phong dep troai")
        binding.person = user1

        binding.apply {
            // read data from Sharepreference
            val data = displayShare()
            if(!data.equals("")){
                txtTitle.text = "Hello $data"
            }
            btnLucky.setOnClickListener(){
                val name :String = edtName.text.toString()
                if(!name.equals(""))
                    txtTitle.text = "Hello ${edtName.text}"
                saveInShare(name)
                val i = Intent(this@LuckyNumber, LuckyNumberActivity::class.java)
                i.putExtra("name", name)
                startActivity(i)
            }
        }

        supportActionBar?.title = "Lucky number"
    }
    fun saveInShare(name: String) {
        // 0 - open the sharePreference
        val sharedPreferences : SharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCE,
            AppCompatActivity.MODE_PRIVATE
        )
        // 1 - edit the share
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.apply()
    }

    fun displayShare(): String? {
        val sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCE,
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.getString("name", "")
    }


}