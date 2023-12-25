package com.example.recipeapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.api.RetrofitInstance
import com.example.recipeapp.database.FoodDao
import com.example.recipeapp.entity.Categories
import com.example.recipeapp.entity.Category
import com.example.recipeapp.entity.CategoryByFilter
import com.example.recipeapp.entity.Ingrediatns
import com.example.recipeapp.entity.Meal
import com.example.recipeapp.utils.Constants.Companion.TAG
import retrofit2.Response

class FoodRepository(val dao: FoodDao) {

    suspend fun getCategoriesFromApi() {
        val response = RetrofitInstance.api.getCategories()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                try {
                    deleteAllMainCategories()
                    Log.e(TAG, "Categories DELETED FROM Database")
                    saveCategories(it.categories)
                    Log.e(TAG, "Categories Saved IN Database")
                } catch (e: Exception) {
                    Log.e(TAG, "CATCH BLOCK IN CATEGORIES API ${e.message}")
                }
            }
        } else {
            Log.e(TAG, "Response is null from API")
        }
    }

    suspend fun getCategoriesByFilter(categoryName: String): Response<CategoryByFilter>? {
        val response = RetrofitInstance.api.filterByCategory(categoryName)
        return if (response.isSuccessful && response.body() != null) {
            Log.e(TAG, "Meals is Correct")
            response
        } else {
            Log.e(TAG, "Response  failed ${response.errorBody()}")
            null
        }
    }

    suspend fun getIngredients(ingredientName: String): Response<Ingrediatns>? {
        val response = RetrofitInstance.api.getIngredients(ingredientName)
        return if (response.isSuccessful && response.body() != null) {
            Log.e(TAG, "Ingredients is Correct and Name is ${response.body()!!.meals[0].strMeal}")
            response
        } else {
            Log.e(TAG, "Response  failed ${response.errorBody()}")
            null
        }
    }


    fun clearAllCategories() {
        dao.deleteAllCategories()
    }

    ///////CATEGORIES DATABASE
    suspend fun saveCategories(categories: List<Category>) {
        dao.insertCategoryIntoDB(categories)
    }

    fun getCategories(): LiveData<List<Category>> {
        return dao.getAllCategoriesFromDB()
    }

    fun deleteAllMainCategories() {
        dao.deleteAllCategories()
    }
}