package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemRecipesBinding
import com.example.recipeapp.entity.Meal
import com.example.recipeapp.ui.fragments.CategoriesFragmentDirections
import com.squareup.picasso.Picasso

class MealRvAdapter : RecyclerView.Adapter<MealRvAdapter.MyViewHolder>() {
    private var mealList = emptyList<Meal>()
    private var navigation = Navigation

    inner class MyViewHolder(val binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRecipesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMeal = mealList[position]
        holder.binding.apply {
            recipeName.text = currentMeal.strMeal
            Picasso.get().load(currentMeal.strMealThumb).into(recipeImg)
            itemRescipe.setOnClickListener { view ->
                val action =
                    CategoriesFragmentDirections.actionCategoriesFragmentToIngrediantsFragment2(
                        currentMeal.strMeal
                    )
                view.findNavController().navigate(action)
            }
        }
    }

    fun setData(mealListHere: List<Meal>) {
        mealList = mealListHere
        notifyDataSetChanged()
    }
}