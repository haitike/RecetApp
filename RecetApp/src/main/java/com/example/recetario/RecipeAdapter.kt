package com.example.recetario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeDatabase: RecipeDatabase)   : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curRecipe = recipeDatabase.getRecipe(position)

        holder.itemView.apply {
            val tvRecipeTitle = holder.itemView.findViewById<TextView>(R.id.tvRecipeTitle)
            val tvServings = holder.itemView.findViewById<TextView>(R.id.tvRecipeServings)
            val tvCalories = holder.itemView.findViewById<TextView>(R.id.tvRecipeCalories)
            tvRecipeTitle.text = curRecipe.name
            tvServings.text = context.getString(R.string.servings_amount, curRecipe.servings.toString())
            tvCalories.text = context.getString(R.string.amount_cal_per_serving, curRecipe.calories.toString())
        }
    }

    override fun getItemCount(): Int {
        return recipeDatabase.getRecipeCount()
    }
}