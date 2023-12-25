package com.example.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.entity.Categories
import com.example.recipeapp.entity.Category

@Database(entities = [Category::class], version = 1, exportSchema = false)
abstract class FoodDataBase : RoomDatabase() {
    abstract fun getDao(): FoodDao

    companion object {
        @Volatile
        var INSTANCE: FoodDataBase? = null
        fun createDataBase(context: Context): FoodDataBase {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            instance = Room.databaseBuilder(context, FoodDataBase::class.java, "food_Db").build()
            return instance
        }
    }

}