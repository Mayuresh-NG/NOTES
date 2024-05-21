package com.example.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())
    val notes: LiveData<MutableList<Note>> = _notes

    fun addNote(note: Note) {
        _notes.value?.add(note)
        _notes.value = _notes.value
    }

    fun updateNote(updatedNote: Note) {
        val index = _notes.value?.indexOfFirst { it.id == updatedNote.id }
        index?.let {
            if (it != -1) {
                _notes.value?.set(it, updatedNote)
                _notes.value = _notes.value
            }
        }
    }
}
