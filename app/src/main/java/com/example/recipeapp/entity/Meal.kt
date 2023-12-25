package com.example.recipeapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)