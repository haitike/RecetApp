package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    companion object {
        var recipes: MutableList<Recipe> = mutableListOf()
    }

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

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
        notifyItemInserted(recipes.size - 1)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curRecipe = recipes[position]
        holder.itemView.apply {
            val tvRecipeTitle = holder.itemView.findViewById<TextView>(R.id.tvRecipeTitle)
            val tvServings = holder.itemView.findViewById<TextView>(R.id.tvRecipeServings)
            val tvCalories = holder.itemView.findViewById<TextView>(R.id.tvRecipeCalories)
            tvRecipeTitle.text = curRecipe.name
            tvServings.text = context.getString(R.string.servings, curRecipe.servings.toString())
            tvCalories.text = context.getString(R.string.cal_per_serving, curRecipe.calories.toString())
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}