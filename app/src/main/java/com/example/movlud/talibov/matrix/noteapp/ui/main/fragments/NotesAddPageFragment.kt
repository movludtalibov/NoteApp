package com.example.movlud.talibov.matrix.noteapp.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movlud.talibov.matrix.noteapp.R
import com.example.movlud.talibov.matrix.noteapp.databinding.FragmentNotesAddPageBinding
import com.example.movlud.talibov.matrix.noteapp.ui.main.adapters.FirebaseAdapter
import com.example.movlud.talibov.matrix.noteapp.ui.main.models.ModelNotes
import com.example.movlud.talibov.matrix.noteapp.ui.main.viewmodels.EditProfileViewModel
import com.example.movlud.talibov.matrix.noteapp.ui.main.viewmodels.MainScreenViewModel

class NotesAddPageFragment : Fragment() {

    private lateinit var binding : FragmentNotesAddPageBinding
    private val viewModelEdit: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToMain.setOnClickListener {
            goToBack()
        }
        binding.buttonSave.setOnClickListener {
            onSaveClick()
        }

        viewModelEdit.addNoteState.observe(viewLifecycleOwner){
            if(it){
                goToBack()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesAddPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun onSaveClick(){
        val title = binding.titleEditText.text.toString()
        val description = binding.notesEditText.text.toString()
        if (title.isNullOrEmpty().not() || description.isNullOrEmpty().not()){
            val note = ModelNotes(title = title, notes = description)
            viewModelEdit.addNoteToFirebase(note)
        }

    }
    private fun goToBack(){
        findNavController().navigate(R.id.action_notesAddPageFragment_to_mainScreenFragment)
    }

}