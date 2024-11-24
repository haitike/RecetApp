package com.example.recetario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.Spinner
import androidx.fragment.app.Fragment

class PlannerFragment : Fragment() {
    private lateinit var recipeDatabase: RecipeDatabase

    override fun onCreateView(
        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weekly_planner, container, false)

        recipeDatabase = (requireContext() as MainActivity).recipeDatabase
        val recipeList = recipeDatabase.getRecipeList()

        //Populate all the spinners with the recipes
        val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val meals = arrayOf("1", "2", "3")
        for (day in daysOfWeek) {
            for (meal in meals) {
                val spinnerId =
                    resources.getIdentifier("spinner$day$meal", "id", requireContext().packageName)
                val spinner = view.findViewById<Spinner>(spinnerId)
                populateSpinner(spinner, recipeList)
            }
        }


        return view
    }

    fun populateSpinner(spinner: Spinner, recipeList: List<String>) {
        val newRecipeList = arrayListOf("") + recipeList
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, newRecipeList)
        spinner.adapter = adapter

        // Set an OnItemSelectedListener on the Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //println("Selected item: ${recipeList[position - 1]}") // Handle the item selection here
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when no item is selected
            }
        }
    }
}