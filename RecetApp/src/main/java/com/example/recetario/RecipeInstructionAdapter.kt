package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeInstructionAdapter(private val recipeId: Int, private val recipeDatabase: RecipeDatabase)   : RecyclerView.Adapter<RecipeInstructionAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe_instruction,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
            return recipeDatabase.getInstructionsByRecipeId(recipeId).size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curIns = recipeDatabase.getInstructionsByRecipeId(recipeId)[position]

        holder.itemView.apply {
            val tvInstruction = holder.itemView.findViewById<TextView>(R.id.tvRecipeInstruction)
            tvInstruction.text = buildString {
                append((position+1).toString())
                append(". ")
                append(curIns.instruction)
            }

            }
    }
}



