package com.example.recylerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recylerview.databinding.WordlistItemBinding
import java.util.*

class WordListAdapter(private val wordList: LinkedList<String>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = WordlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word_current = wordList[position]
        with(holder.binding){
            textWord.text = word_current
            root.setOnClickListener{
                wordList[position] = "Clicked! $word_current"
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
    inner class WordViewHolder(val binding: WordlistItemBinding) : RecyclerView.ViewHolder(binding.root)
}

