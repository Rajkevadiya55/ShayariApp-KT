package com.example.shayariapp.Adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.Activity.AllShayariActivity
import com.example.shayariapp.Model.ShayariModel
import com.example.shayariapp.R
import com.example.shayariapp.databinding.ItemShayariBinding
import java.util.Locale


class AllShayariAdapter(
    val allShayariActivity: AllShayariActivity, val shayariList: ArrayList<ShayariModel>,
) : RecyclerView.Adapter<AllShayariAdapter.ShayariViewHolder>(),TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null

    init {
        textToSpeech = TextToSpeech(allShayariActivity, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set the language for TextToSpeech (Here, we are using the default locale)
            val result = textToSpeech?.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or language is not supported.
                // You may want to handle this case accordingly.
            }
        } else {
            // TextToSpeech initialization failed.
            // You may want to handle this case accordingly.
        }
    }



    class ShayariViewHolder(val binding: ItemShayariBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {
        return ShayariViewHolder(
            ItemShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = shayariList.size

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {

        if (position % 5 == 0) {
            holder.binding.mainback.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 5 == 1) {
            holder.binding.mainback.setBackgroundResource(R.drawable.gradient_2)
        } else if (position % 5 == 2) {
            holder.binding.mainback.setBackgroundResource(R.drawable.gradient_3)
        } else if (position % 5 == 3) {
            holder.binding.mainback.setBackgroundResource(R.drawable.gradient_4)
        }



        holder.binding.itemshayari.text = shayariList[position].data.toString()

        holder.binding.share.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n Install this application for Shayari\n\n"
                shareMessage = """
                            ${shareMessage + "https://play.google.com/store/apps/details?id="}                    
                         """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayariActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
            true
        }


        holder.binding.copy.setOnClickListener {

            val clipboard: ClipboardManager? =
                allShayariActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayariList[position].data.toString())
            clipboard?.setPrimaryClip(clip)
        }


        holder.binding.speech.setOnClickListener {

            val shayariText = shayariList[position].data.toString()
            textToSpeech?.speak(shayariText, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        }


}

