package com.example.kotlin_learn.WorldCup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kotlin_learn.R

class CountryAdapter(private var activity: WorldCupActivity, private var items: ArrayList<CountryModel>):
    BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view :View?
        val viewHolder:ViewHolder

        // 1 - Initialize the inflate layout
        if(convertView == null){
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.world_cup_item,null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // 2 - Setting the layout
        val country= items[position]
        viewHolder.txtName?.text = country.name
        viewHolder.txtWins?.text = country.wins
        viewHolder.flag_img?.setImageResource(R.drawable.ic_launcher_foreground)
        viewHolder.container?.setOnClickListener(){
            Toast.makeText(parent?.context,country.name,Toast.LENGTH_SHORT).show()
        }
        return view as View
    }
    // 3 - ViewHolder class
    private class ViewHolder(row:View?){
        var txtName :TextView? =null
        var txtWins :TextView? = null
        var flag_img: ImageView? = null
        var container: ConstraintLayout? = null
        init {
            txtName = row?.findViewById(R.id.countryName)
            txtWins = row?.findViewById(R.id.countryWin)
            flag_img = row?.findViewById(R.id.countryFlag)!!
            container = row.findViewById(R.id.container)
        }
    }

}