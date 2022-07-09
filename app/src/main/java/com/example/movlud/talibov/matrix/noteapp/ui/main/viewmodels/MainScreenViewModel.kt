package com.example.movlud.talibov.matrix.noteapp.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movlud.talibov.matrix.noteapp.ui.main.models.ModelNotes
import com.google.firebase.firestore.FirebaseFirestore

class MainScreenViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _dataFirebase = MutableLiveData<List<ModelNotes>?>()
    val dataFirebase: LiveData<List<ModelNotes>?>
        get() = _dataFirebase

    fun addNoteToFirebase(note: ModelNotes){
        _loading.value = true
        firestore.collection(NOTES_COLLECTION)
            .add(note)
            .addOnSuccessListener {
                getNotesFromFirebase()
            }
    }




    fun getNotesFromFirebase(){
        firestore.collection(NOTES_COLLECTION)
            .get()
            .addOnSuccessListener {
                val documents = it.documents
                val notesList = documents.mapNotNull { doc ->
                    val noteModel = doc.toObject(ModelNotes::class.java)
                    noteModel
                }
                _loading.value = false
                _dataFirebase.value = notesList
            }
            .addOnFailureListener {
                _loading.value = false
            }
    }
    fun deleteFromFirebase(note: ModelNotes){
        _loading.value = true
        firestore.collection(NOTES_COLLECTION)
            .document(note.notes)
            .delete()
            .addOnSuccessListener {
                getNotesFromFirebase()
            }
    }

    companion object{
        private const val NOTES_COLLECTION = "notes"
    }
}
