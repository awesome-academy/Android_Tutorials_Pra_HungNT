package com.example.recylerview

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import com.example.recylerview.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(){
    private val mWordList = LinkedList<String>()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRecyclerView()
        binding.floatingButton.setOnClickListener{addNewWord()}

    }
    private fun setRecyclerView() {
        for (i in 0 until 20) {
            mWordList.addLast("Word $i")
        }
        binding.recyclerview.adapter = WordListAdapter( mWordList)
    }

    fun addNewWord(){
        val wordListSize = mWordList.size
        mWordList.addLast("+ Word $wordListSize")
        binding.recyclerview.adapter?.notifyItemInserted(wordListSize)
        binding.recyclerview.smoothScrollToPosition(wordListSize)
    }
}


