package com.example.content_provider

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.content_provider.databinding.ItemContactBinding

class ContactAdapter(private val arrayList: ArrayList<ContactModel>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    init {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact_current = arrayList[position]
        with(holder.binding){
            textName.text = contact_current.name
            textNumber.text = contact_current.number
            imagePhone.setOnClickListener{
                val context = root.context
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${contact_current.number}")
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)
}
