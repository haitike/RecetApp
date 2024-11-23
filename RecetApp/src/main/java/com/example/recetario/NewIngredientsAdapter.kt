package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewIngredientsAdapter : RecyclerView.Adapter<NewIngredientsAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var ingredientList: MutableList<Ingredient> = mutableListOf()

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
        ingredientList.add(Ingredient("Ingredient", 1, UnitOfMeasurement.UNITS))
        notifyItemInserted(ingredientList.size - 1)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curIngredient = ingredientList[position]
        holder.itemView.apply {
            val etNewRecipeIngredientTitle = holder.itemView.findViewById<TextView>(R.id.etNewRecipeIngredientTitle)
            val etNewRecipeIngredientAmount = holder.itemView.findViewById<TextView>(R.id.etNewRecipeIngredientAmount)
            val spinnerNewIngredientUnit = holder.itemView.findViewById<Spinner>(R.id.spinnerNewIngredientUnit)
            etNewRecipeIngredientTitle.text = curIngredient.name
            etNewRecipeIngredientAmount.text = curIngredient.quantity.toString()
            spinnerNewIngredientUnit.setSelection(curIngredient.unit.ordinal)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }
}