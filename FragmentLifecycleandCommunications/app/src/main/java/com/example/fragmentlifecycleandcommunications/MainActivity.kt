package com.example.fragmentlifecycleandcommunications

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentlifecycleandcommunications.Content.SongUtils

class MainActivity : AppCompatActivity() {
    /**biến xác định xem giao diện có đủ rộng để hiển thị hai panel hay không*/
    private var mTwoPane = false
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /** Set the toolbar */
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        /** Lấy danh sách bài hát dưới dạng RecyclerView.*/
        val recyclerView: RecyclerView = findViewById(R.id.song_list)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS)

        /** Bố cục vùng chứa có sẵn không? Nếu vậy, hãy đặt mTwoPane thành true.*/
        mTwoPane = findViewById<View>(R.id.song_detail_container) != null
    }

    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<SongUtils.Song>) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.song_list_content, parent, false)
            return ViewHolder(view)
        }

        /** xử lý dữ liệu cho 1 item trong recyleView */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mItem = mValues[position]//lấy ra item ở vị trí position
            holder.mIdView.text = (position + 1).toString()
            holder.mContentView.text = mValues[position].songTitle

            /** xư lý sự kiện onclick vào 1 item*/
            holder.mView.setOnClickListener { v ->
                if (mTwoPane) {//diện có đủ rộng để hiển thị hai panel
                    /** Lấy vị trí bài hát đã chọn trong danh sách bài hát. */
                    val selectedSong = holder.adapterPosition

                    /** Create new instance of fragment
                    using a fragment transaction. */
                    val fragment = SongDetailFragment.newInstance(selectedSong)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.song_detail_container, fragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    /** dùng intent start SongDetailActivity */
                    val context = v.context
                    val intent = Intent(context, SongDetailActivity::class.java)
                    intent.putExtra(SongUtils.SONG_ID_KEY, holder.adapterPosition)
                    context.startActivity(intent)
                }
            }
        }


        override fun getItemCount(): Int {
            return mValues.size
        }

        /** ánh xạ view */
        inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.findViewById(R.id.id)
            val mContentView: TextView = mView.findViewById(R.id.content)
            var mItem: SongUtils.Song? = null
        }
    }
}