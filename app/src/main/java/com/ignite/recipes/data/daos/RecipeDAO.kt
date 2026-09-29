package com.ignite.recipes.data.daos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.ignite.recipes.data.entities.Recipe
import com.ignite.recipes.utils.DatabaseManager

class RecipeDAO (private val context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(recipe: Recipe): Recipe {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Recipe.COLUMN_NAME, recipe.name)
        values.put(Recipe.COLUMN_IMAGE, recipe.image)
        values.put(Recipe.COLUMN_RATING, recipe.rating)
        values.put(Recipe.COLUMN_REVIEW_COUNT, recipe.reviewCount)
        values.put(Recipe.COLUMN_PREP_TIME, recipe.prepTimeMinutes)
        values.put(Recipe.COLUMN_COOK_TIME, recipe.cookTimeMinutes)
        values.put(Recipe.COLUMN_SERVINGS, recipe.servings)
        values.put(Recipe.COLUMN_DIFFICULTY, recipe.difficulty)
        values.put(Recipe.COLUMN_CUISINE, recipe.cuisine)
        values.put(Recipe.COLUMN_INSTRUCTIONS, recipe.instructions.joinToString { it })
        values.put(Recipe.COLUMN_INGREDIENTS, recipe.ingredients.joinToString { it })

        val newRowId = db.insert(Recipe.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()


        recipe.id = newRowId.toInt()
        return recipe
    }

    fun update(recipe: Recipe) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Recipe.COLUMN_NAME, recipe.name)
        values.put(Recipe.COLUMN_IMAGE, recipe.image)
        values.put(Recipe.COLUMN_RATING, recipe.rating)
        values.put(Recipe.COLUMN_REVIEW_COUNT, recipe.reviewCount)
        values.put(Recipe.COLUMN_PREP_TIME, recipe.prepTimeMinutes)
        values.put(Recipe.COLUMN_COOK_TIME, recipe.cookTimeMinutes)
        values.put(Recipe.COLUMN_SERVINGS, recipe.servings)
        values.put(Recipe.COLUMN_DIFFICULTY, recipe.difficulty)
        values.put(Recipe.COLUMN_CUISINE, recipe.cuisine)
        values.put(Recipe.COLUMN_INSTRUCTIONS, recipe.instructions.joinToString { it })
        values.put(Recipe.COLUMN_INGREDIENTS, recipe.ingredients.joinToString { it })

        val updatedRows = db.update(Recipe.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${recipe.id}", null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()
    }

    fun delete(recipe: Recipe) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Recipe.TABLE_NAME, "${DatabaseManager.COLUMN_NAME_ID} = ${recipe.id}", null)
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    fun deleteAll() {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Recipe.TABLE_NAME, null, null)
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id: Int): Recipe {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Recipe.TABLE_NAME,                         // The table to query
            Recipe.COLUMN_NAMES,       // The array of columns to return (pass null to get all)
            "${DatabaseManager.COLUMN_NAME_ID} = $id",                        // The columns for the WHERE clause
            null,                    // The values for the WHERE clause
            null,                        // don't group the rows
            null,                         // don't filter by row groups
            null                         // The sort order
        )

        var recipe: Recipe? = null

        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val recipeName = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_NAME))
            val recipeImage = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_IMAGE))
            val recipeRating = cursor.getFloat(cursor.getColumnIndex(Recipe.COLUMN_RATING))
            val recipeReviewCount = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_REVIEW_COUNT))
            val recipePrepTime = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_PREP_TIME))
            val recipeCookTime = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_COOK_TIME))
            val recipeServings = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_SERVINGS))
            val recipeDifficulty = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_DIFFICULTY))
            val recipeCuisine = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_CUISINE))
            val recipeInstructions = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_INSTRUCTIONS)).split(", ")
            val recipeIngredients = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_INGREDIENTS)).split(", ")
            //Log.i("DATABASE", "$id -> Recipe: $recipeName, Done: $done")

            recipe = Recipe(id, recipeName, recipeImage, recipePrepTime,
                recipeCookTime, recipeServings, recipeDifficulty,
                recipeCuisine, 0, recipeRating, recipeReviewCount,
                recipeIngredients, recipeInstructions)
        }

        cursor.close()
        db.close()

        return recipe!!
    }

    @SuppressLint("Range")
    fun findAll(): List<Recipe> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Recipe.TABLE_NAME,                 // The table to query
            Recipe.COLUMN_NAMES,     // The array of columns to return (pass null to get all)
            null,                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val list: MutableList<Recipe> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val recipeName = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_NAME))
            val recipeImage = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_IMAGE))
            val recipeRating = cursor.getFloat(cursor.getColumnIndex(Recipe.COLUMN_RATING))
            val recipeReviewCount = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_REVIEW_COUNT))
            val recipePrepTime = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_PREP_TIME))
            val recipeCookTime = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_COOK_TIME))
            val recipeServings = cursor.getInt(cursor.getColumnIndex(Recipe.COLUMN_SERVINGS))
            val recipeDifficulty = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_DIFFICULTY))
            val recipeCuisine = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_CUISINE))
            val recipeInstructions = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_INSTRUCTIONS)).split(", ")
            val recipeIngredients = cursor.getString(cursor.getColumnIndex(Recipe.COLUMN_INGREDIENTS)).split(", ")
            //Log.i("DATABASE", "$id -> Recipe: $recipeName, Done: $done")

            val recipe = Recipe(id, recipeName, recipeImage, recipePrepTime,
                recipeCookTime, recipeServings, recipeDifficulty,
                recipeCuisine, 0, recipeRating, recipeReviewCount,
                recipeIngredients, recipeInstructions)
            list.add(recipe)
        }

        cursor.close()
        db.close()

        return list
    }
}