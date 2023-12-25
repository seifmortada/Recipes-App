package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.recipeapp.databinding.ItemCategoriesBinding
import com.example.recipeapp.entity.Categories
import com.example.recipeapp.entity.Category
import com.example.recipeapp.viewmodel.FoodViewModel
import com.squareup.picasso.Picasso

class CategoriesRvAdapter : RecyclerView.Adapter<CategoriesRvAdapter.MyViewHolder>() {
    private var categoriesList = emptyList<Category>()
    var viewModel: FoodViewModel? = null

    inner class MyViewHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoriesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCategory = categoriesList[position]
        holder.binding.apply {
            categoryName.text = currentCategory.strCategory
            Picasso.get().load(currentCategory.strCategoryThumb).into(categoryImg)
            categoryCard.setOnClickListener {
                viewModel?.getCategoryByFilter(currentCategory.strCategory)
            }
        }
    }

    fun setData(categoryList: List<Category>) {
        categoriesList = categoryList
        notifyDataSetChanged()
    }
}