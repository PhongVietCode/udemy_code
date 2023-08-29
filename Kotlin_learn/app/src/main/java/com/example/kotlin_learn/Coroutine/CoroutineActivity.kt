package com.example.kotlin_learn.Coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.kotlin_learn.R
import com.example.kotlin_learn.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineBinding
    var counter = 0;
    var download: Job? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)
        binding.apply {
            button.setOnClickListener() {
                textView9.text = counter++.toString()
                download?.cancel("Cancel the thread")
                textView11.text = "Cancel the thread"
            }
            button3.setOnClickListener() {
                // use coroutine
                download = CoroutineScope(Dispatchers.IO).launch {
                    download()
                }

            }
        }
    }


    private suspend fun download() {
        for (i in 1..100000) {
            Log.d("TAGY", "download: $i")
            withContext(Dispatchers.Main){
            binding.textView10.text = "Downloading $i percent from ${Thread.currentThread().name}"

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        download?.cancel()
    }
}


