package com.example.notes.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.notes.ViewModel.NoteViewModel
import com.example.notes.R
import com.example.notes.View.Adapters.GridAdapter

class Fragment2 : Fragment() {

    private lateinit var myGrid: GridView
    private val noteViewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myGrid = view.findViewById(R.id.list_view)

        val adapter = GridAdapter(requireContext(), noteViewModel.notes.value ?: mutableListOf())
        myGrid.adapter = adapter

        myGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val note = noteViewModel.notes.value?.get(position)
            note?.let {
                noteViewModel.setCurrentNote(it)
                val editDialog = EditNoteDialogFragment.newInstance()
                editDialog.show(parentFragmentManager, "EditNoteDialogFragment")
            }
        }

        noteViewModel.notes.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }
    }
}
