package com.example.shayariapp.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.Activity.AllShayariActivity
import com.example.shayariapp.MainActivity
import com.example.shayariapp.Model.CategoryModel
import com.example.shayariapp.databinding.ItemCategoryBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.cateViewHolder>() {

    val colorsList = arrayListOf<String>("#1abc9c", "#e67e22", "#3498db", "#ED4C67")

    class cateViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cateViewHolder {

        return cateViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: cateViewHolder, position: Int) {

        if (position % 5 == 0) {
            holder.binding.txtitem.setBackgroundColor(Color.parseColor(colorsList[0]))
        } else if (position % 5 == 1) {
            holder.binding.txtitem.setBackgroundColor(Color.parseColor(colorsList[1]))
        } else if (position % 5 == 2) {
            holder.binding.txtitem.setBackgroundColor(Color.parseColor(colorsList[2]))
        } else if (position % 5 == 3) {
            holder.binding.txtitem.setBackgroundColor(Color.parseColor(colorsList[3]))
        }

        holder.binding.txtitem.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {

            var intent = Intent(mainActivity, AllShayariActivity::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            mainActivity.startActivity(intent)
        }
    }
}