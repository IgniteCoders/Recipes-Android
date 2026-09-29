package com.ignite.recipes.data.serviceapis

import com.ignite.recipes.data.entities.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeServiceApi {
    @GET("recipes/?limit=0")
    suspend fun findAll() : Response<RecipeResponse>

    @GET("recipes/search")
    suspend fun findAllByName(@Query("q") query:String) : Response<RecipeResponse>

    @GET("recipes/{id}")
    suspend fun findById(@Path("id") id:Int) : Response<Recipe>
}