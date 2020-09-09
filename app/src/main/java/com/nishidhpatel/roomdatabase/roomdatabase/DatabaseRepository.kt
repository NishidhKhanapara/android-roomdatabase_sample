package com.nishidhpatel.roomdatabase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.nishidhpatel.roomdatabase.model.AddNewWord

//N!SH!DHPATEL

class DatabaseRepository(private val dataBaseDao: DataBaseDao)
{

    val wordList:LiveData<List<AddNewWord>> = dataBaseDao.getList()

    @WorkerThread
    suspend fun insert(addNewWord: AddNewWord){
        dataBaseDao.insert(addNewWord)
    }

    @WorkerThread
    suspend fun delete(id:Int){
        dataBaseDao.delete(id)
    }



}