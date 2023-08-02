package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import com.example.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonWeb.setOnClickListener {openWebsite()}
        binding.buttonLocation.setOnClickListener {openLocal()}
        binding.buttonSharetext.setOnClickListener {openShareText()}
    }

    fun openWebsite(){
        var uri: Uri = Uri.parse(binding.textInputWeb.text.toString())
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }

    fun openLocal(){
        var uri: Uri = Uri.parse(URL_KEY + binding.textInputLocation.text.toString() )
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }

    fun openShareText(){
        ShareCompat.IntentBuilder.from(this)
            .setText(binding.textInputSharetext.text.toString())
            .setChooserTitle(CHOOSER_TITLE_KEY)
            .setType(TEXT_TYPE_KEY)
            .startChooser()
    }

    companion object {
        const val URL_KEY = "geo:O,O?q="
        const val CHOOSER_TITLE_KEY = "ChooserTitle"
        const val TEXT_TYPE_KEY = "text/plain"
    }

}


