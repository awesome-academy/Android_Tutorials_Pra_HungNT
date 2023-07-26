package com.example.fragments.ex_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fragments.R
import com.example.fragments.databinding.ActivityEx2Binding


class Activity_ex2 : AppCompatActivity() {
    private val binding:ActivityEx2Binding by lazy {
        ActivityEx2Binding.inflate(layoutInflater)
    }

    private var isFragmentDisplayed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /**
         *  Mã kiểm tra trạng thái của Fragment đã được hiển thị hay chưa từ Bundle savedInstanceState.
         *  Nếu Fragment đã được hiển thị trước đó, mã sẽ cập nhật nút open/close Fragment để hiển thị chữ "close".
         * Sau đó, mã đặt một lắng nghe sự kiện cho nút open/close Fragment.
         *  Khi người dùng nhấn vào nút, mã kiểm tra nếu Fragment chưa được hiển thị, thì gọi phương thức displayFragment()
         *  để hiển thị Fragment, ngược lại, gọi phương thức closeFragment() để đóng Fragment.
         */
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                binding.openButton.setText(R.string.close)
            }
        }
        /**
         * set sự kiên onclick tương ứng
         */
        binding.openButton.setOnClickListener(View.OnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        })
    }


    /**
     * hiển thị fragment
     */
    fun displayFragment() {
        /**
         *  Instantiate the fragment.
         */

        val fragment_ex2 = Fragment_ex2.newInstance()
        /** sử dụng fragmentManager*/
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()

        /**thêm fragment vào Activity*/
        fragmentTransaction.add(
            R.id.fragment_container_2,
            fragment_ex2
        ).addToBackStack(null).commit()

        /** Update text*/
        binding.openButton!!.setText(R.string.close)
        /** set fragment đã mở*/
        isFragmentDisplayed = true
    }


    /**đóng fragment*/
    fun closeFragment() {
        /**Get the FragmentManager.*/
        val fragmentManager = supportFragmentManager
        /** kiểm tra FRagment có hiện đang hiển thị không*/
        val fragment_ex2 = fragmentManager
            .findFragmentById(R.id.fragment_container_2) as Fragment_ex2?
        if (fragment_ex2 != null) {
            /**nếu fragment đang hiển thị thì đóng nó*/
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment_ex2).commit()
        }
        /**update Text*/
        binding.openButton!!.setText(R.string.open)
        /** set la đã đóng fragment*/
        isFragmentDisplayed = false
    }
    /**    lưu trang thái Activity khi nó bị hủy bỏ hoặc quay lại
    lưu trạng thái Fragment vài  Bundle để khôi phục lại sau này*/
    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        /**lưu trạng thái fragment open or close*/
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }

    companion object {
        /**sử dụng làm khóa khi lưu và khôi phục trạng thái của Fragment trong Bundle.*/
        const val STATE_FRAGMENT = "state_of_fragment"
    }
}



