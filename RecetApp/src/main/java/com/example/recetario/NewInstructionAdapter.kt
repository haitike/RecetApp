package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewInstructionAdapter : RecyclerView.Adapter<NewInstructionAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var instructionAmount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe_new_instruction,
                parent,
                false
            )
        )
    }

    fun addExtraInstruction() {
        this.instructionAmount++
        notifyItemInserted(instructionAmount)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.apply {
            val etNewInstruction = holder.itemView.findViewById<TextView>(R.id.etNewInstruction)
            etNewInstruction.hint = holder.itemView.context.getString(R.string.new_instruction_verbose)
        }
    }

    override fun getItemCount(): Int {
        return this.instructionAmount
    }
}