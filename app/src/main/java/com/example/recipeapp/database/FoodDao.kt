package com.example.recipeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.entity.Categories
import com.example.recipeapp.entity.Category

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryIntoDB(category: List<Category>)

    @Query("SELECT * FROM categoryList_table")
    fun getAllCategoriesFromDB():LiveData<List<Category>>

    @Query("DELETE  FROM categoryList_table")
    fun deleteAllCategories()


}