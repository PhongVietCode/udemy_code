package com.example.kotlin_learn.Firebase.Fragment

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin_learn.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FireStoreFrag : Fragment() {
    private lateinit var text: TextView
    private lateinit var btnGet: Button
    private lateinit var btnAdd: Button
    private lateinit var edtCloud: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fire_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        val collection = db.collection("Car List")
        text = view.findViewById(R.id.textCloud)
        btnGet = view.findViewById(R.id.btnGet)
        edtCloud = view.findViewById(R.id.edtCloud)
        btnAdd = view.findViewById(R.id.btnAddcloud)


        // 1 - Add data to the database
        btnAdd.setOnClickListener() {
            val data: String = edtCloud.text.toString()

            if (data != "") {
                val map = hashMapOf(
                    "name" to data
                )
                collection.document().set(map)
                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
            }
            edtCloud.text.clear()
            it.hideKeyboard()
        }
        btnGet.setOnClickListener() {
            // 0 - get data from database
            text.text = ""
            collection.get().addOnSuccessListener { result ->
                CoroutineScope(Dispatchers.Default).launch {
                    addAll(result)
                }
            }
            it.hideKeyboard()
        }

        // 2 - listen for change of the collection
        collection.addSnapshotListener() { items, e ->
            if (e != null) {
                Log.w("TAG", "listen:error", e)
                return@addSnapshotListener
            }
            CoroutineScope(Dispatchers.IO).launch {
                addText(items)
            }
        }

    }

    private suspend fun addAll(result: QuerySnapshot?) {
        if (result != null) {
            var allDoc :String = ""
            for (item in result) {
                allDoc += "${item.data["name"]}\n"
                Log.d("POG", "addAll: ${item.data["name"]}")
            }
            withContext(Dispatchers.Main){
                text.text = allDoc
            }
        }
    }

    private suspend fun addText(result: QuerySnapshot?) {
        if (result != null) {
            for (item in result.documentChanges) { // when a document is add or remove
                if (item.type == DocumentChange.Type.ADDED) {
                    withContext(Dispatchers.Main) {
                        text.append(item.document.data["name"].toString() + "has been added \n")
                    }
                }
                if (item.type == DocumentChange.Type.REMOVED) {
                    withContext(Dispatchers.Main) {
                        text.append(item.document.data["name"].toString() + " has been removed \n")
                    }
                }

            }
        }
    }
    private fun View.hideKeyboard(){
        val inputManager : InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken,0)
    }
}