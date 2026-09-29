package com.ignite.recipes.data.serviceapis

import com.ignite.recipes.data.entities.Recipe
import com.google.gson.annotations.SerializedName

class RecipeResponse (
    @SerializedName("total") val total:String,
    @SerializedName("skip") val skip:String,
    @SerializedName("limit") val limit:String,
    @SerializedName("recipes") val results:List<Recipe>
) {
}