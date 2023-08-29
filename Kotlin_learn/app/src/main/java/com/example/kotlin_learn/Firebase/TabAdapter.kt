package com.example.kotlin_learn.Firebase

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_learn.Firebase.Fragment.FireStoreFrag
import com.example.kotlin_learn.Firebase.Fragment.RealtimeDatabaseFrag

class TabAdapter(fragmentManager:FragmentManager, lifecycle:Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 ->
                return RealtimeDatabaseFrag()
            1 ->
                return FireStoreFrag()
        }
        return RealtimeDatabaseFrag()
    }

}