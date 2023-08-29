package com.example.kotlin_learn.Vacine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_learn.R
import com.example.kotlin_learn.Vacine.VaccineAdapter.*

class VaccineAdapter(val items:ArrayList<VaccineModel>) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img:ImageView
         var name:TextView
        init {
            img = itemView.findViewById(R.id.imageView)
            name = itemView.findViewById(R.id.textView)
            itemView.setOnClickListener(){
                Toast.makeText(itemView.context, "hhelo",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.img.setImageResource(R.drawable.hd_wallpaper_new_fantasy_pink_blue_gradient_simple_colors)

    }
}