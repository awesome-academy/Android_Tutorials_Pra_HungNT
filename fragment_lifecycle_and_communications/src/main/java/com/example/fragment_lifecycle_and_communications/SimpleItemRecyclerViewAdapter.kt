package com.example.fragment_lifecycle_and_communications

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment_lifecycle_and_communications.databinding.SongItemBinding

class SimpleItemRecyclerViewAdapter (
    private val mValues: List<SongUtils.Song>) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SongItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = mValues[position]
        with(holder.binding) {
            val context = root.context
            id.text = (position + 1).toString()
            content.text = context.getString(song.songTitle)

            root.setOnClickListener { v ->
                val intent = Intent(context, SongDetailActivity::class.java)
                intent.putExtra(SongUtils.SONG_ID_KEY, position)
                context.startActivity(intent)
            }
        }
    }


    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val binding: SongItemBinding) : RecyclerView.ViewHolder(binding.root)
}
