package com.example.recetario

enum class UnitOfMeasurement(val displayName: String) {
    UNITS("units"),
    MILLILITERS("ml."),
    GRAMS("grams")
}

data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: UnitOfMeasurement
)

data class Recipe (
    val name: String,
    val servings: Int,
    val calories: Int,
    val ingredients: List<Ingredient>,
    val instructions: List<String>
)

data class Day(
    val name: String,
    val recipes: List<Recipe>
)