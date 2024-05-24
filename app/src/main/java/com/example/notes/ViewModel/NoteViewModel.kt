package com.example.notes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.Model.Note
import com.example.notes.Model.NoteRepository

class NoteViewModel : ViewModel() {
    private val noteRepository = NoteRepository()
    val notes: LiveData<MutableList<Note>> = noteRepository.getNotes()

    private val _currentNote = MutableLiveData<Note?>()
    val currentNote: LiveData<Note?> = _currentNote

    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    fun updateNote(updatedNote: Note) {
        noteRepository.updateNote(updatedNote)
    }

    fun setCurrentNote(note: Note) {
        _currentNote.value = note
    }

    fun clearCurrentNote() {
        _currentNote.value = null
    }
}
