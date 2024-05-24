package com.example.notes.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.notes.Model.Note
import com.example.notes.R

class GridAdapter(private val context: Context, private var noteList: List<Note>) : BaseAdapter() {
    override fun getCount(): Int {
        return noteList.size
    }

    override fun getItem(position: Int): Any {
        return noteList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false)
        val titleTextView: TextView = view.findViewById(R.id.title_text_view)
        val descriptionTextView: TextView = view.findViewById(R.id.description_text_view)

        val note = noteList[position]
        titleTextView.text = note.title
        descriptionTextView.text = note.description

        return view
    }
}
