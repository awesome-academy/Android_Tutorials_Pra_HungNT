package com.example.fragmentlifecycleandcommunications

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import com.example.fragmentlifecycleandcommunications.Content.SongUtils


class SongDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        /** toolbar*/
        val toolbar = findViewById<View>(R.id.detail_toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)

        /** lấy ActionBar của Activity để tùy chỉnh và điều hướng.*/
        val actionBar = supportActionBar

        /** Dòng này hiển thị nút Up (<-) trên ActionBar. Nếu người dùng chọn nút Up,
        phương thức onOptionsItemSelected() sẽ được gọi.*/
        actionBar?.setDisplayHomeAsUpEnabled(true)


        /** kiểm tra xem Activity có được khôi phục từ trạng thái trước đó hay không */
        if (savedInstanceState == null) {
            /**lấy giá trị selectedSong từ Intent được truyền vào khi khởi chạy Activity.
            Giá trị này thể hiện vị trí bài hát được chọn.*/
            val selectedSong = intent.getIntExtra(SongUtils.SONG_ID_KEY, 0)
            /** tạo một instance của SongDetailFragment và truyền selectedSong vào thông qua phương thức newInstance().*/
            val fragment = SongDetailFragment.newInstance(selectedSong)

            supportFragmentManager.beginTransaction()
                .add(R.id.song_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            /**   phương thức kiểm tra xem người dùng đã chọn nút Up (nút quay lại) hay không.
            Nếu đúng, nó sẽ sử dụng NavUtils để điều hướng đến MainActivity_Fragment_lifecycle
            (một Activity khác trong ứng dụng) */
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}