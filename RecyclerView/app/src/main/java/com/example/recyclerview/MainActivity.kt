package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val mWordList = LinkedList<String>()

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: WordListAdapter

    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until 20) {
            mWordList.addLast("Word $i")
        }

        initView()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        mAdapter = WordListAdapter(this, mWordList)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.bt_fab)

        fab.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v == fab) {
            val wordListSize = mWordList.size
            mWordList.addLast("+ Word $wordListSize")
            mRecyclerView.adapter?.notifyItemInserted(wordListSize)
            mRecyclerView.smoothScrollToPosition(wordListSize)
        }
    }
}
