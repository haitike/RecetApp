package com.example.recetario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewRecipeFragment : Fragment() {
    private lateinit var newIngredientsAdapter: NewIngredientsAdapter
    private lateinit var newInstructionAdapter: NewInstructionAdapter
    private lateinit var recyclerViewIngredients: RecyclerView
    private lateinit var recyclerViewInstructions: RecyclerView

    private lateinit var recipeDatabase: RecipeDatabase


    override fun onCreateView(
        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_new, container, false)

        recipeDatabase = (requireContext() as MainActivity).recipeDatabase

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

                val recipeId = recipeDatabase.insertRecipe(recipeTitle, servings, calories)

                // Ingredient Operations
                for (i in 0 until newIngredientsAdapter.itemCount) {
                    val viewIngredient = recyclerViewIngredients.getChildAt(i)

                    val ingredientTitle = viewIngredient.findViewById<EditText>(R.id.etNewRecipeIngredientTitle).text.toString()
                    val ingredientQuantity = viewIngredient.findViewById<EditText>(R.id.etNewRecipeIngredientAmount).text.toString().toIntOrNull() ?: 0
                    val ingredientUnitPosition = viewIngredient.findViewById<Spinner>(R.id.spinnerNewIngredientUnit).selectedItemPosition
                    val newIngredient = Ingredient(name = ingredientTitle, quantity = ingredientQuantity, unit = UnitOfMeasurement.entries[ingredientUnitPosition])
                    recipeDatabase.insertIngredient(newIngredient, recipeId)
                }

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
