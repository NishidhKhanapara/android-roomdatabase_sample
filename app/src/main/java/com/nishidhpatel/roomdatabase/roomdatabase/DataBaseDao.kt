package com.nishidhpatel.roomdatabase

import com.nishidhpatel.roomdatabase.model.AddNewWord
import androidx.lifecycle.LiveData
import androidx.room.*

//N!SH!DHPATEL

@Dao
interface DataBaseDao
{

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(storeResponse: AddNewWord)

    @Query("SELECT * from words")
    fun getList():LiveData<List<AddNewWord>>

    @Query("DELETE FROM words WHERE id =:wordId")
    suspend fun delete(wordId : Int)


}