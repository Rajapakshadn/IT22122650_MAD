package com.example.taskfinal5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu

import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController

import com.example.taskfinal5.databinding.FragmentAddNoteBinding
import com.example.taskfinal5.model.note_final
import com.example.taskfinal5.viewmodel.NoteViewModel
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import com.example.taskfinal5.MainActivity
import com.example.taskfinal5.R

class AddNoteFragment : Fragment(R.layout.fragment_add_note),MenuProvider {

    private var a_Note_binding: FragmentAddNoteBinding? = null
    private val note_Binding get() =a_Note_binding!!


    private lateinit var n_V_Model: NoteViewModel
    private lateinit var note_view_Add: View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        a_Note_binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        return note_Binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notes_menu_host : MenuHost = requireActivity()
        notes_menu_host.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        n_V_Model = (activity as MainActivity).n_V_Model
        note_view_Add = view

    }

    private fun saveNote(view: View){
        val title_of_THE_NOTE = note_Binding.addthenotetitle.text.toString().trim()
        val desc_of_the_note = note_Binding.addthenotedescription.text.toString().trim()

        if (title_of_THE_NOTE.isNotEmpty()){
            val the_note = note_final(0,title_of_THE_NOTE,desc_of_the_note)
            n_V_Model.addNote(the_note)

            Toast.makeText(note_view_Add.context,"Note saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)

        }
        else{
            Toast.makeText(note_view_Add.context,"please enter the note title ",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(yhe_menu_item: MenuItem): Boolean {
        return when (yhe_menu_item.itemId) {
            R.id.saveMenu -> {
                saveNote(note_view_Add)
                true
            }

            else ->
                false

        }

    }
        override fun onDestroy() {
            super.onDestroy()
            a_Note_binding = null
        }
    }


