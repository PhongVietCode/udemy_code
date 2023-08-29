package com.example.kotlin_learn.Navigation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlin_learn.R
import com.example.kotlin_learn.databinding.FragmentEatingBinding
import com.example.kotlin_learn.databinding.FragmentHome2Binding

class EatingFragment : Fragment() {
    private lateinit var binding : FragmentEatingBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_eating,container,false  )
        val input  = arguments!!.getString("name","")
        binding.textView4.text = "Hello ${input}"
        (activity as AppCompatActivity).supportActionBar?.title = "Eating Zone" // setting action bar title
        return binding.root

    }
}