package com.example.taskfinal5.fragments

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost

import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.taskfinal5.MainActivity

import com.example.taskfinal5.model.note_final
import com.example.taskfinal5.viewmodel.NoteViewModel
import android.view.Menu
import android.view.MenuInflater

import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.taskfinal5.R
import com.example.taskfinal5.adapter.NoteAdapter
import com.example.taskfinal5.databinding.FragmentHomeBinding
import android.view.MenuItem

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,MenuProvider {

    private var bind_home : FragmentHomeBinding? = null
    private val bind_bind get() = bind_home!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        // Inflate the layout for this fragment
        bind_home=FragmentHomeBinding.inflate(inflater,container,false)


        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner,Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).n_V_Model
        setupHomeRecyclerView()

        bind_bind.addnotebutton.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
        return bind_bind.root


    }
    private fun updateUI(note : List<note_final>?){
        if (note != null){
            if(note.isNotEmpty()){
                bind_bind.emptyNotesImage.visibility = View.GONE
                bind_bind.homeRecyclerView.visibility = View.VISIBLE

            }else{
                bind_bind.emptyNotesImage.visibility = View.VISIBLE
                bind_bind.homeRecyclerView.visibility = View.GONE
            }
        }

    }

    private fun setupHomeRecyclerView(){
        noteAdapter = NoteAdapter()
        bind_bind.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let{
            noteViewModel.getAllNotes().observe(viewLifecycleOwner){
                note ->
                noteAdapter.differnotes.submitList(note)
                updateUI(note)
            }
        }
    }
    private fun searchtheNote(query: String?){//search part in the home
        val searchq1 = "%$query"

        noteViewModel.searchNotes(searchq1).observe(this){ list->
            noteAdapter.differnotes.submitList(list)

        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!=null){
            searchtheNote(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bind_home = null
    }

    override fun onCreateMenu(menu123: Menu, menuInflater: MenuInflater) {
        menu123.clear()
        menuInflater.inflate(R.menu.home_menu, menu123)

        val menuSearch = menu123.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }


}