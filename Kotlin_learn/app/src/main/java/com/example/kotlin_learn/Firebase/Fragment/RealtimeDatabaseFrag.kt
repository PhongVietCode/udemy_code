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
import com.example.kotlin_learn.Firebase.Car
import com.example.kotlin_learn.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Objects

class RealtimeDatabaseFrag : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var listCar :TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_realtime_database, container, false)
    }
    private val CAR_LIST:String = "Car_List"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 0 - init firebase database
        // reference: https://kotlin-learn-e1f3e-default-rtdb.firebaseio.com
        database = Firebase.database.reference

        // 1 - Write data to db
//        database.child("price").setValue("19028 $")
        // 2 -  read data from db
        //  a. initialize the listener
//        val postListener =  object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val price  = snapshot.value
//                Toast.makeText(context,"price was change to $price", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        }
        //  b. assign the listener to the value on firebase
//        database.child("price").addValueEventListener(postListener)

        val name: EditText = view.findViewById<EditText?>(R.id.edCarname)
        val type: EditText = view.findViewById<EditText?>(R.id.edCarType)
        val price: EditText = view.findViewById<EditText?>(R.id.edCarprice)

        val btn = view.findViewById<Button>(R.id.btnPost)
        listCar = view.findViewById(R.id.listCartext)
        btn.setOnClickListener(){
            val nametxt:String = name.text.toString()
            val typetxt:String = type.text.toString()
            val pricetxt:String = price.text.toString()
            if(nametxt != ""){
                val car =Car(nametxt, typetxt, pricetxt)
                database.child(CAR_LIST).child(nametxt).setValue(car)
            }
            name.text.clear()
            type.text.clear()
            price.text.clear()
            it.hideKeyboard()
        }
        val postListener = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                 val newcar = snapshot.getValue<Car>()
                listCar.text = ""
                CoroutineScope(Dispatchers.IO).launch {
                    addText(snapshot)
//                    withContext(Dispatchers.Main){
//                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.child(CAR_LIST).addValueEventListener(postListener)

    }

    private suspend fun addText(list: DataSnapshot) {
        for(item in list.children){
            val newCar =item.getValue<Car>()
            withContext(Dispatchers.Main){
                listCar.append("${newCar?.carName} sold for ${newCar?.price}\n")
            }
        }

    }

    private fun View.hideKeyboard(){
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken,0)
    }
}