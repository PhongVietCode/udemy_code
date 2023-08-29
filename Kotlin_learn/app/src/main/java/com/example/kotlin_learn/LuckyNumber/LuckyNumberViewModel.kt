package com.example.kotlin_learn.LuckyNumber

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class LuckyNumberViewModel(startingNum:Int):ViewModel() {
     var counter : Int =generateNumber();

    var number = MutableLiveData<Int>()
    init {
        number.value = startingNum
    }
    fun updateCounter(){
        number.value = number.value?.plus(1)
    }
    fun generateNumber(): Int {
        return Random.nextInt(1000)
    }
    fun getCurrentCounter():Int{
        return counter
    }

}