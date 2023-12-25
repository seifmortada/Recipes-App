package com.example.recipeapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("categoryList_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val idDb: Int,
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)