package com.example.recetario

import android.content.Context
import java.util.UUID

enum class UnitOfMeasurement(val displayName: String) {
    UNITS("units"),
    MILLILITERS("ml."),
    GRAMS("grams")
}

data class Recipe(
    val id: Int? = null,
    val name: String,
    val servings: Int,
    val calories: Int,
    private val context: Context // Add a context parameter to the Recipe class
) {
    fun save() {
        val db = RecipeDatabase(context)
        val database = db.openDatabase()
        db.insertRecipe(this)
        db.close()
    }

    fun update() {
        val db = RecipeDatabase(context)
        val database = db.openDatabase()
        db.updateRecipe(this)
        db.close()
    }

    fun delete() {
        val db = RecipeDatabase(context)
        val database = db.openDatabase()
        db.deleteRecipe(this)
        db.close()
    }
}

data class Ingredient(
    val name: String,
    val quantity: Int,
    val unit: UnitOfMeasurement
)

data class Day(
    val name: String,
    val recipes: List<Recipe>
)