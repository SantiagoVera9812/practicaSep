package com.example.sep6.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sep6.R
import com.example.sep6.modelo.Cancion


class CancionArrayAdapter(context:Context, private val itemList: List<Cancion>) : ArrayAdapter<Cancion>(context, 0, itemList ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        var listItemView = convertView

        if(listItemView == null){

            listItemView = LayoutInflater.from(context).inflate(R.layout.contactsrow,parent,false)
        }

        val curremtItem = itemList[position]

        val dateTextView = listItemView?.findViewById<TextView>(R.id.date)
        val categoryTextView = listItemView?.findViewById<TextView>(R.id.category)
        val nameTextView = listItemView?.findViewById<TextView>(R.id.name)
        val ratingTextView = listItemView?.findViewById<TextView>(R.id.rating)

        dateTextView?.text = "date ${curremtItem.date}"
        categoryTextView?.text = "${curremtItem.category}"
        nameTextView?.text = "${curremtItem.name}"
        ratingTextView?.text = "${curremtItem.avarage_rating}"

        return listItemView!!

    }
}