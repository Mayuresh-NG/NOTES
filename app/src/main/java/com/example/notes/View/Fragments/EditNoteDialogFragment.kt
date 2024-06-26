package com.example.notes.View.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.notes.Model.Note
import com.example.notes.R
import com.example.notes.ViewModel.NoteViewModel
import com.google.android.material.textfield.TextInputEditText

class EditNoteDialogFragment : DialogFragment() {

    private val noteViewModel: NoteViewModel by activityViewModels()
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = noteViewModel.currentNote.value
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleEditText: TextInputEditText = view.findViewById(R.id.title_text_field)
        val descriptionEditText: TextInputEditText = view.findViewById(R.id.description_text_field)

        note?.let { note ->
            titleEditText.setText(note.title)
            descriptionEditText.setText(note.description)
        }

        titleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                note?.title = s.toString()
            }
        })

        descriptionEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                note?.description = s.toString()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        note?.let {
            noteViewModel.updateNote(it)
        }
        noteViewModel.clearCurrentNote()
    }

    companion object {
        fun newInstance(): EditNoteDialogFragment {
            return EditNoteDialogFragment()
        }
    }
}
