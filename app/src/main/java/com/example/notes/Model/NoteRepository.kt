package com.example.notes.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NoteRepository {

    private val notes = MutableLiveData<MutableList<Note>>(mutableListOf())

    fun getNotes(): LiveData<MutableList<Note>> {
        return notes
    }

    fun addNote(note: Note) {
        notes.value?.add(note)
        notes.value = notes.value
    }

    fun updateNote(updatedNote: Note) {
        val index = notes.value?.indexOfFirst { it.id == updatedNote.id }
        index?.let {
            if (it != -1) {
                notes.value?.set(it, updatedNote)
                notes.value = notes.value
            }
        }
    }
}
