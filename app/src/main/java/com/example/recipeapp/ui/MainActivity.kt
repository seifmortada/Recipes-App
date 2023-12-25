package com.example.recipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.R
import com.example.recipeapp.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: FoodViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}