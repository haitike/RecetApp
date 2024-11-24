package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewIngredientsAdapter : RecyclerView.Adapter<NewIngredientsAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var ingredientAmount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe_new_ingredient,
                parent,
                false
            )
        )
    }

    fun addExtraIngredient() {
        this.ingredientAmount++
        notifyItemInserted(ingredientAmount)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.apply {
            val etNewRecipeIngredientTitle = holder.itemView.findViewById<TextView>(R.id.etNewRecipeIngredientTitle)
            val etNewRecipeIngredientAmount = holder.itemView.findViewById<TextView>(R.id.etNewRecipeIngredientAmount)
            val spinnerNewIngredientUnit = holder.itemView.findViewById<Spinner>(R.id.spinnerNewIngredientUnit)
            etNewRecipeIngredientTitle.text = holder.itemView.context.getString(R.string.ingredient)
            etNewRecipeIngredientAmount.text = "1"
            spinnerNewIngredientUnit.setSelection(0)

            val btnDeleteNewIngredient = holder.itemView.findViewById<Button>(R.id.btnDeleteNewIngredient)
            btnDeleteNewIngredient.setOnClickListener {
                notifyItemRemoved(position)
                ingredientAmount--
            }


        }
    }

    override fun getItemCount(): Int {
        return this.ingredientAmount
    }


}