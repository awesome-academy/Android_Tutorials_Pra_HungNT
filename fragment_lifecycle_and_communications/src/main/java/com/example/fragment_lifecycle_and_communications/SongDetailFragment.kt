package com.example.fragment_lifecycle_and_communications

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_lifecycle_and_communications.databinding.FragmentSongDetailBinding

class SongDetailFragment : Fragment() {
    var mSong: SongUtils.Song? = null

    private val binding: FragmentSongDetailBinding  by lazy {
        FragmentSongDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Load the content specified by the fragment arguments
         */
        if (arguments?.containsKey(SongUtils.SONG_ID_KEY) == true) {
            val songId = arguments?.getInt(SongUtils.SONG_ID_KEY)
            if (songId != null) {
                mSong = SongUtils.SONG_ITEMS[songId]
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = binding.root

        /**
         * show song details if mSong is not null
         */
        mSong?.let {
            binding.songDetail.text = getString(it.details)
        }

        return rootView
    }

    companion object {
        fun newInstance(selectedSong: Int): SongDetailFragment {
            val fragment = SongDetailFragment()
            val arguments = Bundle()
            arguments.putInt(SongUtils.SONG_ID_KEY, selectedSong)
            fragment.arguments = arguments
            return fragment
        }
    }
}

