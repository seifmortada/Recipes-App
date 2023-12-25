package com.example.recipeapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.FoodDataBase
import com.example.recipeapp.entity.Categories
import com.example.recipeapp.entity.Category
import com.example.recipeapp.entity.CategoryByFilter
import com.example.recipeapp.entity.Ingrediatns
import com.example.recipeapp.entity.Meal
import com.example.recipeapp.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    val repository: FoodRepository
    private val dataBase: FoodDataBase

    var categories: MutableLiveData<List<Category>> = MutableLiveData()

    var mealsFromView: MutableLiveData<CategoryByFilter> = MutableLiveData()

    var ingredient: MutableLiveData<Ingrediatns> = MutableLiveData()


    init {
        dataBase = FoodDataBase.createDataBase(application.applicationContext)
        repository = FoodRepository(dataBase.getDao())
    }

    fun getCategoryByFilter(categoryName: String) = viewModelScope.launch(Dispatchers.Main) {
        mealsFromView.value = repository.getCategoriesByFilter(categoryName)?.body()
    }



    fun getCategoryFromRepo() = viewModelScope.launch(Dispatchers.IO) {
        repository.getCategoriesFromApi()
    }

    fun getIngredients(ingredientName: String) = viewModelScope.launch(Dispatchers.Main) {
        ingredient.value = repository.getIngredients(ingredientName)?.body()
    }

    //Delete All categories form database
    fun clearAllCategories() = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.clearAllCategories()
        } catch (e: Exception) {
            Log.e("seif", "clearAllCategories:${e.message} ")
        }

    }

    //Get Categories From database
    fun getCategories(): LiveData<List<Category>> {
        return repository.getCategories()
    }
}
