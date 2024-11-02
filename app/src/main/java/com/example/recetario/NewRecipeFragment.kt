package com.example.recetario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewRecipeFragment : Fragment() {
    private lateinit var newIngredientsAdapter: NewIngredientsAdapter
    private lateinit var newInstructionAdapter: NewInstructionAdapter
    private lateinit var recyclerViewIngredients: RecyclerView
    private lateinit var recyclerViewInstructions: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_new, container, false)

        newIngredientsAdapter = NewIngredientsAdapter()
        newInstructionAdapter = NewInstructionAdapter()

        recyclerViewIngredients = view.findViewById(R.id.rvAddRecipeIngredients)
        recyclerViewIngredients.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewIngredients.adapter = newIngredientsAdapter

        recyclerViewInstructions = view.findViewById(R.id.rvAddRecipeInstructions)
        recyclerViewInstructions.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewInstructions.adapter = newInstructionAdapter

        val btnReturnToRecipes: Button = view.findViewById(R.id.btnReturnToRecipes)
        btnReturnToRecipes.setOnClickListener {
            activity?.onBackPressed()
        }

        val btnSaveRecipe: Button = view.findViewById(R.id.btnSaveRecipe)
        btnSaveRecipe.setOnClickListener {

            val recipeTitle = view.findViewById<EditText>(R.id.etAddRecipeTitle).text.toString()
            if (recipeTitle.isNotEmpty()) {
                val recipeServings = view.findViewById<EditText>(R.id.etAddRecipeServings).text.toString()
                val recipeCalories = view.findViewById<EditText>(R.id.etAddRecipeCalories).text.toString()

                val servings = if (recipeServings.isEmpty()) 1 else recipeServings.toInt()
                val calories = if (recipeCalories.isEmpty()) 0 else recipeCalories.toInt()

                recipeList.add(Recipe(recipeTitle, servings, calories, mutableListOf(), mutableListOf()))

                activity?.onBackPressed()
            }
        }

        val btnAddIngredient: Button = view.findViewById(R.id.btnAddIngredient)
        btnAddIngredient.setOnClickListener {
            newIngredientsAdapter.addExtraIngredient()
        }

        val btnAddInstruction: Button = view.findViewById(R.id.btnAddInstruction)
        btnAddInstruction.setOnClickListener {
            newInstructionAdapter.addExtraInstruction()
        }

        return view
    }


}
