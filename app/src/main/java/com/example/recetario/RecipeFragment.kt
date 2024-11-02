package com.example.recetario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_manager, container, false)

        recipeAdapter = RecipeAdapter()

        recyclerView = view.findViewById(R.id.rvRecipeList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recipeAdapter

        // Add some sample recipes
        val btnAddRecipe: Button = view.findViewById(R.id.btnAddRecipe)
        btnAddRecipe.setOnClickListener {
            showNewRecipeFragment()
        }
        return view
    }

    private fun showNewRecipeFragment() {
        val newRecipeFragment = NewRecipeFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, newRecipeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}