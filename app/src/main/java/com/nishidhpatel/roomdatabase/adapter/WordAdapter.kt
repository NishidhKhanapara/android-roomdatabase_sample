package com.nishidhpatel.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nishidhpatel.roomdatabase.model.AddNewWord
import kotlinx.android.synthetic.main.row_word.view.*

//N!SH!DHPATEL

class WordAdapter(private val lists:ArrayList<AddNewWord>) : RecyclerView.Adapter<WordAdapter.DataViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_word, parent, false))

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(lists[position])
    }

    fun addUsers(list: List<AddNewWord>) {
        this.lists.apply {
            clear()
            addAll(list)
        }

    }

    class DataViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(data: AddNewWord){
            itemView.apply {
                tvWord.text=data.word
            }
        }
    }
}