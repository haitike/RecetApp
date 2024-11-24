package com.example.recetario

data class Recipe(
    val id: Int? = null,
    val name: String,
    val servings: Int,
    val calories: Int,
)

data class Ingredient(
    val id: Int? = null,
    val recipeId: Int? = null,
    val name: String,
    val quantity: Int,
    val unit: Int
)

data class Instruction(
    val id: Int? = null,
    val recipeId: Int? = null,
    val instruction: String
)