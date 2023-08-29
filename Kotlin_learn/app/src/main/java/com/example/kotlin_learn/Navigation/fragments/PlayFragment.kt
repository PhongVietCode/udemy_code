package com.example.kotlin_learn.Navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlin_learn.R
import com.example.kotlin_learn.databinding.FragmentHome2Binding
import com.example.kotlin_learn.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {
    private lateinit var binding : FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_play,container,false  )
        return binding.root
    }
}