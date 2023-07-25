package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import com.example.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object {
        const val URL_KEY = "geo:O,O?q="
        const val CHOOSER_TITLE_KEY = "ChooserTitle"
        const val TEXT_TYPE_KEY = "text/plain"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun openWebsite(view: View){
        var uri: Uri = Uri.parse(binding.textInputWeb.text.toString())
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }

    fun openLocal(view: View){
        var uri: Uri = Uri.parse(URL_KEY + binding.textInputLocation.text.toString() )
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }

    fun openShareText(view: View){
        ShareCompat.IntentBuilder.from(this)
            .setText(binding.textInputSharetext.text.toString())
            .setChooserTitle(CHOOSER_TITLE_KEY)
            .setType(TEXT_TYPE_KEY)
            .startChooser()
    }
}




