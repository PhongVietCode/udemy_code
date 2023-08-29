package com.example.kotlin_learn.Navigation.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlin_learn.R
import com.example.kotlin_learn.databinding.FragmentHome2Binding
import org.w3c.dom.Text

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home2, container, false)
        binding.btnHomeSubmit.setOnClickListener() {
            if (!TextUtils.isEmpty(binding.editTextText.text.toString())) {
                val bundle = bundleOf("name" to binding.editTextText.text.toString()) // key is name
                it.findNavController().navigate(
                    R.id.action_homeFragment_to_eatingFragment,
                    bundle
                ) // pass by insert to the second argument
            }
        }

        return binding.root

    }

}