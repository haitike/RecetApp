package com.example.recetario

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class RecipeDatabase(private val context: Context) {
    private val databaseName = "RecipeDatabase.db"

    private var db: SQLiteDatabase? = null

    fun openDatabase(): SQLiteDatabase {
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
                append("(id INTEGER PRIMARY KEY AUTOINCREMENT, recipeId INTEGER, name TEXT, quantity TEXT, unit TEXT)")
            }
        val CREATE_INSTRUCTIONS_TABLE =
            buildString {
                append("CREATE TABLE if not exists instructions ")
                append("(id INTEGER PRIMARY KEY AUTOINCREMENT, recipeId INTEGER, instruction TEXT)")
            }

        db?.execSQL(CREATE_RECIPES_TABLE)
        db?.execSQL(CREATE_INGREDIENTS_TABLE)
        db?.execSQL(CREATE_INSTRUCTIONS_TABLE)
    }

    fun close() {
        try {
            db?.close()
        } catch (e: Exception) {
            // Log the exception or handle it in some other way
            Log.e("RecipeDatabase", "Error closing database connection", e)
        } finally {
            db = null
        }
    }

    fun insertRecipe(recipe: Recipe) {
        val db = openDatabase()
        val contentValues = ContentValues().apply {
            put("name", recipe.name)
            put("servings", recipe.servings)
            put("calories", recipe.calories)
        }
        db.insert("recipes", null, contentValues)
    }

    fun updateRecipe(recipe: Recipe) {
        val db = openDatabase()
        val contentValues = ContentValues().apply {
            put("name", recipe.name)
            put("servings", recipe.servings)
            put("calories", recipe.calories)
        }
        db.update("recipes", contentValues, "id = ?", arrayOf(recipe.id.toString()))

    }

    fun deleteRecipe(recipe: Recipe) {
        db?.delete("recipes", "id = ?", arrayOf(recipe.id.toString()))
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
            context = context
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


}