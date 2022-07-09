package com.example.movlud.talibov.matrix.noteapp.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movlud.talibov.matrix.noteapp.R
import com.example.movlud.talibov.matrix.noteapp.databinding.FragmentMainScreenBinding
import com.example.movlud.talibov.matrix.noteapp.ui.main.adapters.FirebaseAdapter
import com.example.movlud.talibov.matrix.noteapp.ui.main.models.ModelNotes
import com.example.movlud.talibov.matrix.noteapp.ui.main.viewmodels.MainScreenViewModel
import com.google.firebase.ktx.Firebase


class MainScreenFragment : Fragment() {

    private lateinit var binding : FragmentMainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()
    private val adapter by lazy { FirebaseAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileButton.setOnClickListener {
            goToProfileDetails()
        }
        setupRecycler()
        setupAddButton()



        viewModel.getNotesFromFirebase()
        viewModel.dataFirebase.observe(viewLifecycleOwner){newDataList ->
            newDataList.let { list->
                adapter.submitList(list?.toMutableList())
            }

            viewModel.loading.observe(viewLifecycleOwner){isLoading ->
                setLoading(isLoading)

            }

        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun goToNotesAddScreen(){
        findNavController().navigate(R.id.action_mainScreenFragment_to_notesAddPageFragment)
    }
    private fun goToProfileDetails(){
        findNavController().navigate(R.id.action_mainScreenFragment_to_profileFragment)
    }

    private fun setLoading(isLoading: Boolean){
        binding.notesProgress.isVisible = isLoading
    }
    private fun setupAddButton() {
        binding.buttonAdd.setOnClickListener {
            goToNotesAddScreen()
        }
    }
    private fun setupRecycler() {
        binding.notesRecycler.adapter = adapter
        adapter.setOnItemClick { note ->
            onItemFirebaseDelete(note)
        }
    }
    private fun onItemFirebaseDelete(note: ModelNotes) {
        viewModel.deleteFromFirebase(note)
    }


}