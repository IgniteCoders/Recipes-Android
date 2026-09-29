package com.ignite.recipes.data.entities

import com.ignite.recipes.utils.DatabaseManager
import com.google.gson.annotations.SerializedName

class Recipe (
    @SerializedName("id") var id:Int,
    @SerializedName("name") var name:String,
    @SerializedName("image") var image:String,
    @SerializedName("prepTimeMinutes") var prepTimeMinutes:Int,
    @SerializedName("cookTimeMinutes") var cookTimeMinutes:Int,
    @SerializedName("servings") var servings:Int,
    @SerializedName("difficulty") var difficulty:String,
    @SerializedName("cuisine") var cuisine:String,
    @SerializedName("caloriesPerServing") var caloriesPerServing:Int,
    @SerializedName("rating") var rating:Float,
    @SerializedName("reviewCount") var reviewCount:Int,
    @SerializedName("ingredients") var ingredients:List<String>,
    @SerializedName("instructions") var instructions:List<String>
) {

    companion object {
        const val TABLE_NAME = "Recipes"
        const val COLUMN_NAME = "name"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_RATING = "rating"
        const val COLUMN_REVIEW_COUNT = "review_count"
        const val COLUMN_PREP_TIME = "prep_time_minutes"
        const val COLUMN_COOK_TIME = "cook_time_minutes"
        const val COLUMN_SERVINGS = "servings"
        const val COLUMN_DIFFICULTY = "difficulty"
        const val COLUMN_CUISINE = "cuisine"
        const val COLUMN_INSTRUCTIONS = "instructions"
        const val COLUMN_INGREDIENTS = "ingredients"
        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME,
            COLUMN_IMAGE,
            COLUMN_RATING,
            COLUMN_REVIEW_COUNT,
            COLUMN_PREP_TIME,
            COLUMN_COOK_TIME,
            COLUMN_SERVINGS,
            COLUMN_DIFFICULTY,
            COLUMN_CUISINE,
            COLUMN_INSTRUCTIONS,
            COLUMN_INGREDIENTS
        )
    }

    val time: Int
        get() = prepTimeMinutes + cookTimeMinutes
}