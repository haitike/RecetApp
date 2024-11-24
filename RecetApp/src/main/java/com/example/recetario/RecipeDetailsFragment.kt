package com.example.recetario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeDetailsFragment(private val recipe: Recipe) : Fragment() {

    private lateinit var ingredientsAdapter: RecipeIngredientsAdapter
    private lateinit var instructionAdapter: RecipeInstructionAdapter
    private lateinit var recyclerViewIngredients: RecyclerView
    private lateinit var recyclerViewInstructions: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_details, container, false)


        //RecyclerView Stuff
        ingredientsAdapter = RecipeIngredientsAdapter(recipe.id!!, (requireContext() as MainActivity).recipeDatabase)
        instructionAdapter = RecipeInstructionAdapter(recipe.id, (requireContext() as MainActivity).recipeDatabase)

        recyclerViewIngredients = view.findViewById(R.id.rvRecipeIngredients)
        recyclerViewIngredients.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewIngredients.adapter = ingredientsAdapter

        recyclerViewInstructions = view.findViewById(R.id.rvRecipeInstructions)
        recyclerViewInstructions.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewInstructions.adapter = instructionAdapter

        // Get references to the TextViews
        val tvRecipeTitle = view.findViewById<TextView>(R.id.tvRecipeTitle)
        val tvServings = view.findViewById<TextView>(R.id.tvRecipeServings)
        val tvCalories = view.findViewById<TextView>(R.id.tvRecipeCalories)

        // Populate the TextViews with the Recipe object values
        tvRecipeTitle.text = recipe.name
        tvServings.text = context?.getString(R.string.servings_amount, recipe.servings.toString())
        tvCalories.text = context?.getString(R.string.amount_cal_per_serving, recipe.calories.toString())

        val btnReturnToRecipes: Button = view.findViewById(R.id.btnReturnToRecipes)
        btnReturnToRecipes.setOnClickListener {
            activity?.onBackPressed()
        }

        val btnDeleteRecipe : Button = view.findViewById(R.id.btnDeleteRecipe)
        btnDeleteRecipe.setOnClickListener {
            (requireContext() as MainActivity).recipeDatabase.deleteRecipe(recipe)
            activity?.onBackPressed()
        }


        return view



    }
}
