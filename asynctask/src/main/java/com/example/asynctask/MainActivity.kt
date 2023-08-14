package com.example.asynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.asynctask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.progressBar.visibility =View.INVISIBLE
        if (savedInstanceState != null) {
            binding.textStatus.text = savedInstanceState.getString(TEXT_STATE)
        }

        binding.buttonStart.setOnClickListener{startTask(it)}
    }


    fun startTask(view: View) {
        binding.textStatus.text = getString(R.string.napping)
        binding.progressBar.progress = 0

        SimpleAsyncTask(binding.textStatus,binding.progressBar).execute()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, binding.textStatus.toString())
    }

    companion object{
        private const val TEXT_STATE = "currentText"
    }
}

