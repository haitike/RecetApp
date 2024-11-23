package com.example.recetario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewInstructionAdapter : RecyclerView.Adapter<NewInstructionAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var instructionList: MutableList<String> = mutableListOf()

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
        instructionList.add("@string/new_instruction_verbose")
        notifyItemInserted(instructionList.size - 1)

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curInstruction = instructionList[position]
        holder.itemView.apply {
            val etNewInstruction = holder.itemView.findViewById<TextView>(R.id.etNewInstruction)
            etNewInstruction.text = curInstruction
        }
    }

    override fun getItemCount(): Int {
        return instructionList.size
    }
}