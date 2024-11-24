package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeIngredientsAdapter(private val recipeId: Int, private val recipeDatabase: RecipeDatabase)   : RecyclerView.Adapter<RecipeIngredientsAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe_ingredient,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
            return recipeDatabase.getIngredientsByRecipeId(recipeId).size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curIngredient = recipeDatabase.getIngredientsByRecipeId(recipeId)[position]

        holder.itemView.apply {
            val tvIngredientTitle = holder.itemView.findViewById<TextView>(R.id.tvRecipeIngredientTitle)
            val tvIngredientAmount = holder.itemView.findViewById<TextView>(R.id.tvRecipeIngredientAmount)
            val tvIngredientUnit = holder.itemView.findViewById<TextView>(R.id.tvRecipeIngredientUnit)
            val units = context.resources.getStringArray(R.array.units_choices)
            tvIngredientTitle.text = curIngredient.name
            tvIngredientAmount.text = curIngredient.quantity.toString()
            tvIngredientUnit.text = units[curIngredient.unit]

            }
    }
}



