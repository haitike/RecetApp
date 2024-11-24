package com.example.recetario

import android.content.Context

enum class UnitOfMeasurement(val displayNameResId: Int) {
    UNITS(0),
    MILLILITERS(1),
    GRAMS(2),
    TBSP(3);

    companion object {
        fun getDisplayNames(context: Context): Array<String> {
            val unitsChoices = context.resources.getStringArray(R.array.units_choices)
            return unitsChoices
        }

        fun getDisplayName(context: Context, unit: UnitOfMeasurement): String {
            val unitsChoices = context.resources.getStringArray(R.array.units_choices)
            return unitsChoices[unit.displayNameResId]
        }
    }

}

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
    val unit: UnitOfMeasurement
)

data class Instruction(
    val id: Int? = null,
    val recipeId: Int,
    val instruction: String
)