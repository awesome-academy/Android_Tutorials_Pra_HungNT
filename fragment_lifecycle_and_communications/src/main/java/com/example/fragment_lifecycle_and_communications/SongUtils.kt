package com.example.fragment_lifecycle_and_communications


object SongUtils {

    val SONG_ITEMS: MutableList<Song> = ArrayList()

    const val SONG_ID_KEY = "item_id"
    private const val COUNT = 7
    data class Song(val songTitle: Int, val details: Int)
    init {
        /**
         * Fill the array with songs.
         */
        for (i in 0 until COUNT) {
            addItem(createSongAtPosition(i))
        }
    }
    /**
     * add Song_Items
     */
    private fun addItem(item: Song) {
        SONG_ITEMS.add(item)
    }
    /**
     * create Song Title and Song Detail
     */

    private fun createSongAtPosition(position: Int): Song {
        val newTitle: Int
        val newDetail: Int
        when (position) {
            0 -> {
                newTitle = R.string.title_0
                newDetail = R.string.detail_0
            }
            1 -> {
                newTitle = R.string.title_1
                newDetail = R.string.detail_1
            }
            2 -> {
                newTitle = R.string.title_2
                newDetail = R.string.detail_2
            }
            3 -> {
                newTitle = R.string.title_3
                newDetail = R.string.detail_3
            }
            4 -> {
                newTitle = R.string.title_4
                newDetail = R.string.detail_4
            }
            5 -> {
                newTitle = R.string.title_5
                newDetail = R.string.detail_5
            }
            else -> {
                newTitle = R.string.title_6
                newDetail = R.string.detail_6
            }
        }
        return Song(newTitle, newDetail)
    }
}


