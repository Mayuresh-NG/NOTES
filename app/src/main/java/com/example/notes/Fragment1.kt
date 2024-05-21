package com.example.notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.UUID

class Fragment1 : Fragment() {

    private val noteViewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextFieldLayout: TextInputLayout = view.findViewById(R.id.title_text_field_layout)
        val descriptionTextFieldLayout: TextInputLayout = view.findViewById(R.id.description_text_field_layout)
        val titleEditText: TextInputEditText = view.findViewById(R.id.title_text_field)
        val descriptionEditText: TextInputEditText = view.findViewById(R.id.description_text_field)
        val saveButton: Button = view.findViewById(R.id.save_button)

        val fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton2)
        fab.setOnClickListener {
            val visibility = if (titleTextFieldLayout.visibility == View.GONE) View.VISIBLE else View.GONE
            titleTextFieldLayout.visibility = visibility
            descriptionTextFieldLayout.visibility = visibility
            saveButton.visibility = visibility
        }

        titleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                titleTextFieldLayout.error = null
            }
        })

        descriptionEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                descriptionTextFieldLayout.error = null
            }
        })

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            var valid = true
            if (title.isEmpty()) {
                titleTextFieldLayout.error = "Please enter a title"
                valid = false
            } else {
                titleTextFieldLayout.error = null
            }

            if (description.isEmpty()) {
                descriptionTextFieldLayout.error = "Please enter a description"
                valid = false
            } else {
                descriptionTextFieldLayout.error = null
            }

            if (valid) {
                val noteId = UUID.randomUUID().toString() // Generate a unique ID for the note
                noteViewModel.addNote(Note(noteId, title, description))

                titleEditText.text?.clear()
                descriptionEditText.text?.clear()

                titleTextFieldLayout.visibility = View.GONE
                descriptionTextFieldLayout.visibility = View.GONE
                saveButton.visibility = View.GONE
                Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
