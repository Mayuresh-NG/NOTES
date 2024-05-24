package com.example.notes.View.Fragments

import com.example.notes.Model.Note
import com.example.notes.ViewModel.NoteViewModel
import java.util.UUID

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import com.example.notes.R

class AddNoteDialogFragment : DialogFragment() {

    private val noteViewModel: NoteViewModel by activityViewModels()
    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleEditText = view.findViewById(R.id.title_text_field)
        descriptionEditText = view.findViewById(R.id.description_text_field)

    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        if (title.isNotEmpty() && description.isNotEmpty()) {
            val note = Note(
                id = UUID.randomUUID().toString(),
                title = title,
                description = description
            )
            noteViewModel.addNote(note)
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}