package com.example.recipeapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentIngrediantsBinding
import com.example.recipeapp.viewmodel.FoodViewModel
import com.squareup.picasso.Picasso

class IngrediantsFragment : Fragment() {
    private lateinit var binding: FragmentIngrediantsBinding
    private lateinit var viewModel: FoodViewModel
    private val args = navArgs<IngrediantsFragmentArgs>()
    private var youtubeLink: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingrediants, container, false)
        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        val ingredientName = args.value.name
        viewModel.getIngredients(ingredientName)
        binding.progressBar.isVisible = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ingredient.observe(viewLifecycleOwner) {
            if (it != null) {
                val meal = it.meals[0]
                binding.apply {
                    youtubeLink = meal.strYoutube
                    progressBar.isVisible = false
                    instructions.text = meal.strInstructions
                    ingredientName.text = meal.strMeal
                    Picasso.get().load(meal.strMealThumb).into(ingredientImg)
                    ingredient1.text = meal.strIngredient1
                    ingredient2.text = meal.strIngredient2
                    ingredient3.text = meal.strIngredient3
                    ingredient4.text = meal.strIngredient4
                    ingredient5.text = meal.strIngredient5
                    ingredient6.text = meal.strIngredient6
                    ingredient7.text = meal.strIngredient7
                    ingredient8.text = meal.strIngredient8
                    ingredient9.text = meal.strIngredient9
                    ingredientArea.text = meal.strArea
                    ingredientCategory.text = meal.strCategory
                    measure1.text = meal.strMeasure1
                    measure2.text = meal.strMeasure2
                    measure3.text = meal.strMeasure3
                    measure4.text = meal.strMeasure4
                    measure5.text = meal.strMeasure5
                    measure6.text = meal.strMeasure6
                    measure7.text = meal.strMeasure7
                    measure8.text = meal.strMeasure8
                    measure9.text = meal.strMeasure9
                    measure10.text = meal.strMeasure10
                    youtubeBtn.setOnClickListener {
                        goToYoutubePage()
                    }
                }
            }
        }
    }

    private fun goToYoutubePage() {
        val videoUrl = youtubeLink
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(intent)
    }
}
