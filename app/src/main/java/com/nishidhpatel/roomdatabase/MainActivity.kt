package com.nishidhpatel.roomdatabase

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nishidhpatel.roomdatabase.model.AddNewWord
import kotlinx.android.synthetic.main.activity_main.*

//N!SH!DHPATEL

class MainActivity : AppCompatActivity() {

    //roomdatabase
    private lateinit var databaseViewModel : DatabaseViewModel

    //adapter
    private lateinit var adapter: WordAdapter
    var wordList : ArrayList<AddNewWord> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        //initRoomdatabase
        databaseViewModel= ViewModelProviders.of(this).get(DatabaseViewModel::class.java)

        //getWordList
        databaseViewModel.allWordList!!.observe(this, Observer {words->
            if(words.size!=0)
            {
                retrieveList(words)
                rvWordList.visibility=View.VISIBLE
                tvNoWordFound.visibility=View.GONE

            }else{
                rvWordList.visibility=View.GONE
                tvNoWordFound.visibility=View.VISIBLE
            }

        })

        //setSwipListnerInRecyclerView
        swipListnerRecyclerview()


    }

    private fun swipListnerRecyclerview() {
        val touchHelperCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder,
                    target: ViewHolder
                ): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    databaseViewModel.delete(wordList.get(viewHolder.layoutPosition).id)

                }

            }
        val itemTouchHelper = ItemTouchHelper(touchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rvWordList)
    }

    private fun initRecyclerView() {
        rvWordList.layoutManager=LinearLayoutManager(this)
        adapter= WordAdapter(arrayListOf())
        rvWordList.adapter=adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.menu_add){

            showAddWordDialog()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun showAddWordDialog() {
        val dialog = Dialog(this, R.style.picture_dialog_style)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_addkeyword)
        val wlmp: WindowManager.LayoutParams = dialog.getWindow()!!.getAttributes()
        wlmp.gravity = Gravity.CENTER
        wlmp.width = WindowManager.LayoutParams.MATCH_PARENT
        wlmp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.getWindow()!!.setAttributes(wlmp)
        dialog.show()

        val tvCancel = dialog.findViewById(R.id.tvCancel) as TextView
        val tvAddWord=dialog.findViewById(R.id.tvAdd) as TextView
        val etWord=dialog.findViewById(R.id.etNewWord) as EditText


        tvCancel.setOnClickListener { dialog.dismiss() }
        tvAddWord.setOnClickListener {
            if(etWord.text.toString().length > 0){
                val addWord=AddNewWord(etWord.text.toString())
                databaseViewModel.insert(addWord)
                dialog.dismiss()
            }else{
                Toast.makeText(this,"Please enter a new word",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun retrieveList(list: List<AddNewWord>) {
        wordList= list as ArrayList<AddNewWord>
        adapter.apply {
            addUsers(list)
            notifyDataSetChanged()

        }
    }

}
