package com.example.taskfinal5.adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.taskfinal5.fragments.HomeFragment
import com.example.taskfinal5.fragments.HomeFragmentDirections
import com.example.taskfinal5.model.note_final
import androidx.navigation.findNavController
import com.example.taskfinal5.databinding.NoteLayoutBinding


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    class NoteViewHolder(val ibinding: NoteLayoutBinding) : RecyclerView.ViewHolder(ibinding.root)

    private val differnewcb = object : DiffUtil.ItemCallback<note_final>(){        //ditermine the difference between the two list
        override fun areItemsTheSame(oitem: note_final, nitem: note_final): Boolean {       //is there same item
            return oitem.id == nitem.id &&
                    oitem.noteDesc ==nitem.noteDesc &&
                    oitem.noteTitle == nitem.noteTitle
        }

        override fun areContentsTheSame(oitem: note_final, nitem: note_final): Boolean {
            return oitem == nitem
        }

    }
    val differnotes = AsyncListDiffer(this,differnewcb)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differnotes.currentList.size
    }

    override fun onBindViewHolder(hld: NoteViewHolder, pos: Int) {
        val currentNote = differnotes.currentList[pos]

        hld.ibinding.title.text = currentNote.noteTitle
        hld.ibinding.descrption.text = currentNote.noteDesc

        hld.itemView.setOnClickListener{
            val notedirection = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(notedirection)
        }
    }
}