package com.wreckingballsoftware.fiveoclocksomewhere.network

import com.wreckingballsoftware.fiveoclocksomewhere.BuildConfig
import com.wreckingballsoftware.fiveoclocksomewhere.network.models.ApiCocktails
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailDBService {
    @GET("json/v1/${BuildConfig.COCKTAILDB_AUTH_KEY}/search.php")
    suspend fun getCocktail(
        @Query("s") cocktailName: String,
    ): ApiCocktails

    @GET("json/v1/${BuildConfig.COCKTAILDB_AUTH_KEY}/random.php")
    suspend fun getRandomCocktail(): ApiCocktails
}