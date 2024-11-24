package com.example.recetario

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class RecipeDatabase(private val context: Context) {
    private val databaseName = "RecipeDatabase.db"

    private var db: SQLiteDatabase? = null

    private fun openDatabase(): SQLiteDatabase {
        db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null)
        createTables()
        return db!!
    }

    private fun createTables() {
        val CREATE_RECIPES_TABLE =
            buildString {
                append("CREATE TABLE if not exists recipes ")
                append("(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, servings INTEGER, calories INTEGER)")
            }

        val CREATE_INGREDIENTS_TABLE =
            buildString {
                append("CREATE TABLE if not exists ingredients ")
                append("(id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                append("recipeId INTEGER, ")
                append("name TEXT, ")
                append("quantity INTEGER, ")
                append("unit INTEGER, ")
                append("FOREIGN KEY (recipeId) REFERENCES recipes (id))")
            }

        val CREATE_INSTRUCTIONS_TABLE =
            buildString {
                append("CREATE TABLE if not exists instructions ")
                append("(id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                append("recipeId INTEGER, ")
                append("instruction TEXT, ")
                append("FOREIGN KEY (recipeId) REFERENCES recipes (id))")
            }


        val CREATE_RECIPE_DAYS_TABLE =
            buildString {
                append("CREATE TABLE if not exists recipe_days ")
                append("(recipeId INTEGER, dayId INTEGER, ")
                append("PRIMARY KEY (recipeId, dayId), ")
                append("FOREIGN KEY (recipeId) REFERENCES recipes (id), ")
                append("CHECK (dayId BETWEEN 0 AND 6))")
            }


        try {
            db?.execSQL(CREATE_RECIPES_TABLE)
            db?.execSQL(CREATE_INGREDIENTS_TABLE)
            db?.execSQL(CREATE_INSTRUCTIONS_TABLE)
            db?.execSQL(CREATE_RECIPE_DAYS_TABLE)
        } catch (e: Exception) {
            // Log the exception or handle it in some other way
            Log.e("RecipeDatabase", "Error creating tables", e)
        }
    }

    fun insertRecipe(name : String, servings : Int, calories : Int): Int {
        val db = openDatabase()
        val contentValues = ContentValues().apply {
            put("name", name)
            put("servings", servings)
            put("calories", calories)
        }
        val id  = db.insert("recipes", null, contentValues)
        db.close()
        return id.toInt()
    }

    fun updateRecipe(id: Int, name: String, servings: Int, calories: Int) {
        val db = openDatabase()
        val contentValues = ContentValues().apply {
            put("name", name)
            put("servings", servings)
            put("calories", calories)
        }
        db.update("recipes", contentValues, "id = ?", arrayOf(id.toString()))
        db.close()

    }

    fun deleteRecipe(recipe: Recipe) {
        val db = openDatabase()
        db.delete("recipes", "id = ?", arrayOf(recipe.id.toString()))
        db.close()
    }

    fun getRecipe(position: Int): Recipe {
        val db = openDatabase()
        val cursor = db.query("recipes", null, null, null, null, null, null)
        cursor.moveToPosition(position)

        val idIndex = cursor.getColumnIndex("id")
        val nameIndex = cursor.getColumnIndex("name")
        val servingsIndex = cursor.getColumnIndex("servings")
        val caloriesIndex = cursor.getColumnIndex("calories")

        if (idIndex == -1 || nameIndex == -1 || servingsIndex == -1 || caloriesIndex == -1) {
            throw RuntimeException("Invalid column index")
        }

        val recipe = Recipe(
            cursor.getInt(idIndex),
            cursor.getString(nameIndex),
            cursor.getInt(servingsIndex),
            cursor.getInt(caloriesIndex),
        )
        cursor.close()
        db.close()
        return recipe
    }

    fun getRecipeCount(): Int {
        val db = openDatabase()
        val cursor = db.query("recipes", null, null, null, null, null, null)
        val count = cursor.count
        cursor.close()
        db.close()
        return count

    }

    fun insertIngredient(ingredient: Ingredient, recipeId: Int) {
        val db = openDatabase()
        val contentValues = ContentValues().apply {
            put("recipeId", recipeId)
            put("name", ingredient.name)
            put("quantity", ingredient.quantity.toString())
            put("unit", ingredient.unit)
        }
        db.insert("ingredients", null, contentValues)
        db.close()
    }

    fun insertInstruction(instructionText: String, recipeId: Int) {
        val db = openDatabase()
        val contentValues = ContentValues().apply {
            put("recipeId", recipeId)
            put("instruction", instructionText)
        }
        db.insert("instructions", null, contentValues)
        db.close()
    }

    fun getIngredientsByRecipeId(recipeId: Int): List<Ingredient> {
        val db = openDatabase()
        val cursor = db.query("ingredients", null, "recipeId = ?", arrayOf(recipeId.toString()), null, null, null)
        val ingredients = mutableListOf<Ingredient>()
        while (cursor.moveToNext()) {
            val nameIndex = cursor.getColumnIndex("name")
            val quantityIndex = cursor.getColumnIndex("quantity")
            val unitIndex = cursor.getColumnIndex("unit")
            if (nameIndex == -1 || quantityIndex == -1 || unitIndex == -1) {
                throw RuntimeException("Invalid column index")
            }
            val name = cursor.getString(nameIndex)
            val quantity = cursor.getInt(quantityIndex)
            val unit = cursor.getInt(unitIndex)
            ingredients.add(Ingredient(name=name, quantity =quantity, unit = unit))
        }
        cursor.close()
        db.close()
        return ingredients
    }

    fun getInstructionsByRecipeId(recipeId: Int): List<Instruction> {
        val db = openDatabase()
        val cursor = db.query("instructions", null, "recipeId = ?", arrayOf(recipeId.toString()), null, null, null)
        val instructions = mutableListOf<Instruction>()
        while (cursor.moveToNext()) {
            val instructionIndex = cursor.getColumnIndex("instruction")
            if (instructionIndex == -1) {
                throw RuntimeException("Invalid column index")
            }
            val instruction = cursor.getString(instructionIndex)
            instructions.add(Instruction(instruction = instruction))
        }
        cursor.close()
        db.close()
        return instructions
    }

}