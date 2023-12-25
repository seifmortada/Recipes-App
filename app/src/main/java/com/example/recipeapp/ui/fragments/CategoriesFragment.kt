package com.example.recipeapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.adapter.CategoriesRvAdapter
import com.example.recipeapp.adapter.MealRvAdapter
import com.example.recipeapp.databinding.FragmentCategoriesBinding
import com.example.recipeapp.viewmodel.FoodViewModel

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesRvAdapter
    private lateinit var mealsAdapter: MealRvAdapter
    private lateinit var viewModel: FoodViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()

        //Observe the meals
        viewModel.mealsFromView.observe(viewLifecycleOwner) {
            if (it == null) {
                Log.e("seif", "Meals are null")
            } else {
                Log.e("seif", "Meals are not null")
                mealsAdapter.setData(it.meals)
            }
        }

        //Observe the categories Form the ViewModel
        viewModel.getCategories().observe(viewLifecycleOwner) {
            if (it == null) {
                Log.e("seif", "Database is null")
                binding.progressBarCategories.isVisible = true
            } else {
                binding.progressBarCategories.isVisible = false
                categoriesAdapter.setData(it)
            }
        }
    }

    private fun initializeRecyclerView() {
        categoriesAdapter = CategoriesRvAdapter()
        categoriesAdapter.viewModel = viewModel
        mealsAdapter = MealRvAdapter()
        binding.rvCategories.apply {
            adapter = categoriesAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 1, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvRecipes.apply {
            adapter = mealsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getMealsByCategory() {
        //Get all the meals form the API
        viewModel.getCategoryByFilter("Beef")

    }
}

