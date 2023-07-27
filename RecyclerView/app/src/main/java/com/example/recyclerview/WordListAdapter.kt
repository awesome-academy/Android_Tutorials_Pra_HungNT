package com.example.recyclerview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
class WordListAdapter(private val context: Context, private val wordList: LinkedList<String>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView: View = inflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current: String = wordList[position]
        holder.wordItemView.text = current
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    inner class WordViewHolder(
        itemView: View,
        private val adapter: WordListAdapter
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val wordItemView: TextView = itemView.findViewById(R.id.word)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = layoutPosition
            val element = wordList[position]
            wordList[position] = "Clicked! $element"
            adapter.notifyDataSetChanged()
        }
    }

}