package com.example.intentexercise

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n", "QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val defaultUrl = "https://developer.android.com/"
        binding.editTextWebsite.setText(defaultUrl)

        val defaultLatitude = 37.8199109
        val defaultLongitude = -122.4811401
        binding.editTextLocation.setText("Golden Gate Bridge")

        val defaultShareText = "This is the text for sharing"
        binding.editTextShareText.setText(defaultShareText)

        binding.buttonWebsite.setOnClickListener {
            val websiteUrl = binding.editTextWebsite.text.toString()
            val websiteIntent = Intent()
            websiteIntent.action = Intent.ACTION_VIEW
            websiteIntent.addCategory(Intent.CATEGORY_BROWSABLE)
            websiteIntent.data = Uri.parse(websiteUrl)

            if (websiteIntent.resolveActivity(packageManager) != null){
                startActivity(websiteIntent)
            }
        }

        binding.buttonLocation.setOnClickListener {
            val location = binding.editTextLocation.text.toString()
            val mapIntent = Intent()
            mapIntent.`package` = "com.google.android.apps.maps"    // Mở bang ứng dụng GoogleMap
            mapIntent.action = Intent.ACTION_VIEW
            mapIntent.data = Uri.parse("geo:$defaultLatitude,$defaultLongitude")

            if (mapIntent.resolveActivity(packageManager) != null){
                startActivity(mapIntent)
            }
        }

        binding.buttonShareText.setOnClickListener {
            val text = binding.editTextShareText.text.toString()
            val shareTextIntent = Intent()
            shareTextIntent.action = Intent.ACTION_SEND
            shareTextIntent.putExtra(Intent.EXTRA_TEXT, text)
            shareTextIntent.type = "text/plain"

            if (shareTextIntent.resolveActivity(packageManager) != null){
                startActivity(shareTextIntent)
            }
        }
    }
}