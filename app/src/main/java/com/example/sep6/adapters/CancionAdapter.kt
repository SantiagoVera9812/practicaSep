package com.example.sep6.adapters

import android.content.Context
import android.database.Cursor
import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter
import com.example.sep6.R
import org.w3c.dom.Text

class CancionAdapter(context: Context?, c: Cursor?, flags: Int ) : CursorAdapter(context,c,flags){
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.contactsrow,parent,false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        val tvDate = view!!.findViewById<TextView>(R.id.date)
        val tvCategory = view!!.findViewById<TextView>(R.id.category)
        val tvName = view!!.findViewById<TextView>(R.id.name)
        val tvRating = view!!.findViewById<TextView>(R.id.rating)

        val id = cursor!!.getString(0)
        val category = cursor!!.getString(1)
        val name = cursor!!.getString(2)
        val rating = cursor!!.getFloat(3)

        tvDate.text = id
        tvCategory.text = category
        tvName.text = name
        tvRating.text = rating.toString()
    }


}