package com.nishidhpatel.roomdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//N!SH!DHPATEL

@Entity(tableName = "Words")
class AddNewWord(var word:String,@PrimaryKey(autoGenerate = true)var id:Int=0
                 )