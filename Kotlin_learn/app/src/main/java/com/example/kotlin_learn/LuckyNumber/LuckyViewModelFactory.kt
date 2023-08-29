package com.example.kotlin_learn.LuckyNumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class LuckyViewModelFactory(private val startingNum:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LuckyNumberViewModel::class.java)){
            return LuckyNumberViewModel(startingNum) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}