package com.ignite.recipes.utils

import com.ignite.recipes.data.serviceapis.RecipeServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        fun getRecipeServiceApi(): RecipeServiceApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RecipeServiceApi::class.java)
        }
    }
}