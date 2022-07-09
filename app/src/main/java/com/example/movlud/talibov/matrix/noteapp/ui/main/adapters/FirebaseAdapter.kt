package com.example.movlud.talibov.matrix.noteapp.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movlud.talibov.matrix.noteapp.databinding.ItemNoteLayoutBinding
import com.example.movlud.talibov.matrix.noteapp.ui.main.models.ModelNotes

class FirebaseAdapter: ListAdapter<ModelNotes, FirebaseAdapter.MainViewHolder>(MainDiffUtils) {

    private var onItemDelete: ((note: ModelNotes) -> Unit)? = null

    fun setOnItemClick(onItemDelete: ((note: ModelNotes) -> Unit)?){
        this.onItemDelete = onItemDelete

    }

    inner class MainViewHolder(private val binding: ItemNoteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: ModelNotes, onItemDelete: ((note: ModelNotes) -> Unit)?) {
            binding.notes.text = note.notes
            binding.title.text = note.title
            binding.root.setOnClickListener {
                onItemDelete?.invoke(note)
            }
        }
    }


    object MainDiffUtils : DiffUtil.ItemCallback<ModelNotes>() {
        override fun areItemsTheSame(oldItem: ModelNotes, newItem: ModelNotes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ModelNotes, newItem: ModelNotes): Boolean {
            return oldItem.notes == newItem.notes
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding=
            ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position], onItemDelete)
    }
}