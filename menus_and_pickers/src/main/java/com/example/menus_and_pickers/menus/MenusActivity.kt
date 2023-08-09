package com.example.menus_and_pickers.menus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.menus_and_pickers.R
import com.example.menus_and_pickers.databinding.ActivityMenusBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenusActivity : AppCompatActivity() {
   private val binding: ActivityMenusBinding by lazy {
       ActivityMenusBinding.inflate(layoutInflater)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.imageDonut.setOnClickListener{showDonutOrder(it)}
        binding.imageFroyo.setOnClickListener{showFroyoOrder(it)}
        binding.imageIceCream.setOnClickListener{showIceCreamOrder(it)}
        binding.floating.setOnClickListener{StartOrderActivity()}
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage)
                startActivity(intent)
                return true
            }
            R.id.action_status -> {
                displayToast(getString(R.string.action_status_message))
                return true
            }
            R.id.action_favorites -> {
                displayToast(getString(R.string.action_favorites_message))
                return true
            }
            R.id.action_contact -> {
                displayToast(getString(R.string.action_contact_message))
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun showDonutOrder(view: View) {
        mOrderMessage = getString(R.string.donut_order_message)
        displayToast(mOrderMessage)
    }


    fun showIceCreamOrder(view: View) {
        mOrderMessage = getString(R.string.ice_cream_order_message)
        displayToast(mOrderMessage)
    }

    fun showFroyoOrder(view: View) {
        mOrderMessage = getString(R.string.froyo_order_message)
        displayToast(mOrderMessage)
    }

    fun StartOrderActivity() {
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }

    fun displayToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_MESSAGE = "com.example.menus_and_pickers.MESSAGE"
        private var mOrderMessage: String? = null
    }
}
