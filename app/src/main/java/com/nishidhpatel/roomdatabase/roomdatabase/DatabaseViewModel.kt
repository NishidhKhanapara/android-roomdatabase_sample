package com.nishidhpatel.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nishidhpatel.roomdatabase.model.AddNewWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//N!SH!DHPATEL

class DatabaseViewModel(application: Application) :AndroidViewModel(application) {


    private var repository: DatabaseRepository? = null
    var allWordList:LiveData<List<AddNewWord>> ?= null

    init {
        val databaseDao = AppRoomDataBase.getDatabase(application).databseDao()
        repository = DatabaseRepository(databaseDao)
        allWordList=repository!!.wordList

    }

    fun insert(addNewWord: AddNewWord) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.insert(addNewWord)
    }
    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.delete(id)
    }
}