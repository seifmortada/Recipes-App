package com.example.recipeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.viewmodel.FoodViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: FoodViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        val button = findViewById<Button>(R.id.btnGetStarted)
        val progressBar= findViewById<ProgressBar>(R.id.progressBarSplash)
//        viewModel.getCategories().observe(this, Observer {
//            if (it == null) {
//            }
        viewModel.getCategoryFromRepo()

        progressBar.visibility= View.INVISIBLE
            button.visibility=View.VISIBLE
//        })
        button.setOnClickListener {
            val intent=Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}