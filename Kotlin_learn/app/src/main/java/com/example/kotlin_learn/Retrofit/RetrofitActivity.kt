package com.example.kotlin_learn.Retrofit

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.kotlin_learn.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    private lateinit var listAlbums: Albums
    private lateinit var retrofitService: AlbumService
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        text = findViewById(R.id.resTXT)
        retrofitService = RetrofitInstance
            .getRetrofit()
            .create(AlbumService::class.java)
        val edtid: EditText = findViewById(R.id.edtID)
        val edtUserid: EditText = findViewById(R.id.edtuserId)

        val btnRev: Button = findViewById(R.id.btnRetrieve)
        btnRev.setOnClickListener() { it ->
            val id: String = edtid.text.toString()
            val userId: String = edtUserid.text.toString()
            if (userId != "") {
                text.text ="Finding for you..."
                val responseLiveData: LiveData<Response<Albums>> = liveData {
                    val response2 = retrofitService.getSpecificAlbum(userId.toInt())
                    emit(response2)
                }

                responseLiveData.observe(this@RetrofitActivity, Observer {
                    val albums = it.body()?.listIterator()
                    if (albums != null) {
                        CoroutineScope(Dispatchers.Default).launch {
                            retrivieData(albums)
                        }
                    }
                })
                edtUserid.text = null
            }
            it.hideKeyboard()

        }

    }

    private suspend fun retrivieData(albums: MutableListIterator<AlbumItem>) {
        var counter = 0
        text.text  =""

        if (!albums.hasNext()){
            text.append("Not found")
            return;
        }
        while (albums.hasNext()) {
            val albumsItem = albums.next()
            withContext(Dispatchers.Main) {
                text.append(counter++.toString() +": "+ albumsItem.title + "\n")
            }
            Log.d("TAGP", "onCreate: ${albumsItem.title}")
        }
    }
    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}