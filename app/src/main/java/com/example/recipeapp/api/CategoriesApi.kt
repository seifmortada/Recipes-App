package com.example.recipeapp.api

import com.example.recipeapp.entity.Categories
import com.example.recipeapp.entity.CategoryByFilter
import com.example.recipeapp.entity.Ingrediatns
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesApi {
    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<Categories>

    @GET("/api/json/v1/1/filter.php")
    suspend fun filterByCategory(
        @Query("c")
        category: String
    ): Response<CategoryByFilter>

    @GET("/api/json/v1/1/search.php")
    suspend fun getIngredients(
        @Query("s")
        ingredientName:String
    ):Response<Ingrediatns>
}