package com.example.shayariapp.Activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shayariapp.MainActivity
import com.example.shayariapp.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity() {
    lateinit var binding:ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnstart.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.btnrate.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }
        }

        binding.btnmore.setOnClickListener {
            val uri = Uri.parse("market://details?id=$packageName")
            val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
            try {
                startActivity(myAppLinkToMarket)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
            }
        }
    }
}