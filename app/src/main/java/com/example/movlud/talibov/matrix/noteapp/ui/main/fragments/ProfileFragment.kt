package com.example.movlud.talibov.matrix.noteapp.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movlud.talibov.matrix.noteapp.R
import com.example.movlud.talibov.matrix.noteapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToMain2.setOnClickListener {
            backToMainScreenFromEdit()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }



    private fun backToMainScreenFromEdit(){
        findNavController().navigate(R.id.action_profileFragment_to_mainScreenFragment)
    }
}