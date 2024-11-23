package com.example.recetario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var recipeDatabase: RecipeDatabase

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipeDatabase = RecipeDatabase(this)

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()

        // Initialize button variables
        val btnRecipes = findViewById<Button>(R.id.btnRecipes)
        val btnPlanner = findViewById<Button>(R.id.btnPlanner)
        val btnShoppingList = findViewById<Button>(R.id.btnShoppingList)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        // Set up button click listeners
        btnRecipes.setOnClickListener {
            showRecipesFragment()
        }
        btnPlanner.setOnClickListener {
            showPlannerFragment()
        }
        btnShoppingList.setOnClickListener {
            showShoppingListFragment()
        }
        btnSettings.setOnClickListener {
            showSettingsFragment()
        }

        showRecipesFragment()
    }

    private fun showRecipesFragment() {
        fragmentTransaction = fragmentManager.beginTransaction() // Reset the fragmentTransaction object
        val fragment = RecipesFragment()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun showPlannerFragment() {
        fragmentTransaction = fragmentManager.beginTransaction() // Reset the fragmentTransaction object
        val fragment = PlannerFragment()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun showShoppingListFragment() {
        fragmentTransaction = fragmentManager.beginTransaction() // Reset the fragmentTransaction object
        val fragment = ShoppingListFragment()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun showSettingsFragment() {
        fragmentTransaction = fragmentManager.beginTransaction() // Reset the fragmentTransaction object
        val fragment = SettingsFragment()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }
}