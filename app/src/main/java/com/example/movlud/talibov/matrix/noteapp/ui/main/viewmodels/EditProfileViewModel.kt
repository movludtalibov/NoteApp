package com.example.movlud.talibov.matrix.noteapp.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movlud.talibov.matrix.noteapp.ui.main.models.ModelNotes
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileViewModel: ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _addNoteState = MutableLiveData<Boolean>()
    val addNoteState: LiveData<Boolean>
        get() = _addNoteState

    fun addNoteToFirebase(note: ModelNotes){
        _loading.value = true
        firestore.collection(NOTES_COLLECTION)
            .add(note)
            .addOnSuccessListener {
                _addNoteState.value = true
            }
    }

    companion object{
        private const val NOTES_COLLECTION = "notes"
    }
}