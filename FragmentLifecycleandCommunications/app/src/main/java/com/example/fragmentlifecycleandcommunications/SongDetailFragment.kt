package com.example.fragmentlifecycleandcommunications

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fragmentlifecycleandcommunications.Content.SongUtils


class SongDetailFragment : Fragment() {
    /** khai báo đối tượng SongUtils*/
    var mSong: SongUtils.Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(SongUtils.SONG_ID_KEY)) {
            /** kiểm tra xem có tồn tại đối số SongUtils.SONG_ID_KEY trong arguments (bundle) hay không.
            Nếu tồn tại, nó sẽ lấy thông tin chi tiết của bài hát từ SongUtils.SONG_ITEMS
            dựa trên selectedSong (vị trí bài hát được chọn) và lưu trữ nó trong biến mSong.*/
            mSong = SongUtils.SONG_ITEMS[arguments
                ?.getInt(SongUtils.SONG_ID_KEY)!!]
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.song_detail,
            container, false
        )

        /**Nếu mSong không null, nó sẽ hiển thị nội dung chi tiết
        của bài hát trong một TextView trong giao diện người dùng.*/
        if (mSong != null) {
            (rootView.findViewById<View>(R.id.song_detail) as TextView).text = mSong!!.details
        }
        return rootView
    }

    companion object {
        /** truyền dữ liệu giữa Activity và Fragment.*/
        fun newInstance(selectedSong: Int): SongDetailFragment {
            val fragment = SongDetailFragment()
            val arguments = Bundle()
            arguments.putInt(SongUtils.SONG_ID_KEY, selectedSong)
            fragment.arguments = arguments
            return fragment
        }
    }
}