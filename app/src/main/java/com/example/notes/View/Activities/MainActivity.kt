package com.example.notes.View.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.R
import com.example.notes.View.Fragments.AddNoteDialogFragment
import com.example.notes.View.Fragments.BottomSheet
import com.example.notes.View.Fragments.Fragment2
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Fragment2())
                .commit()
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val addNoteDialog = AddNoteDialogFragment()
            addNoteDialog.show(supportFragmentManager, "AddNoteDialogFragment")
        }

        val search: FloatingActionButton = findViewById(R.id.search)
        search.setOnClickListener {
            val bottomSheet = BottomSheet()
            bottomSheet.show(supportFragmentManager, "BottomSheet")
        }
    }
}
