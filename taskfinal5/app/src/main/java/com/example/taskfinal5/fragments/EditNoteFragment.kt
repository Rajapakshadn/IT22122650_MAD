package com.example.taskfinal5.fragments

import android.app.AlertDialog
import android.os.Bundle

import android.view.MenuInflater
import android.view.MenuItem
import android.view.View

import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.example.taskfinal5.databinding.FragmentEditNoteBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu

import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider

import com.example.taskfinal5.model.note_final
import com.example.taskfinal5.viewmodel.NoteViewModel
import com.example.taskfinal5.MainActivity
import com.example.taskfinal5.R
import android.view.ViewGroup
import android.widget.Toast
import java.time.temporal.ValueRange

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditNoteFragment : Fragment(R.layout.fragment_edit_note),MenuProvider {

    private var edit_n_b: FragmentEditNoteBinding? = null
    private val bind get() = edit_n_b!!

    private lateinit var notes_V_N: NoteViewModel
    private lateinit var current_note : note_final

    private val args: EditNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        edit_n_b= FragmentEditNoteBinding.inflate(inflater,container,false)
        return bind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notes_V_N = (activity as MainActivity).n_V_Model
        current_note = args.noteFinal!!

        bind.editNoteTitle.setText(current_note.noteTitle)
        bind.ediththenotedescription.setText(current_note.noteDesc)

        bind.ediththenotedescription.setOnClickListener(){
            val title_of_the_note = bind.editNoteTitle.text.toString().trim()
            val descrption_note = bind.ediththenotedescription.text.toString().trim()

            if (title_of_the_note.isNotEmpty()){
                val the_note = note_final(current_note.id,title_of_the_note,descrption_note)
                notes_V_N.updateNote(the_note)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            }else{
                Toast.makeText(context,"enter the title",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Dlete the Note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                notes_V_N.deleteNote(current_note)
                Toast.makeText(context,"Note Deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }

            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu123: Menu, menu_inflater123: MenuInflater) {
        menu123.clear()
        menu_inflater123.inflate(R.menu.menu_edit_note,menu123)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu ->{
                deleteNote()
                true
            }else->
                false
        }


        }
    override fun onDestroy() {
        super.onDestroy()
        edit_n_b=null
    }


}