package com.wreckingballsoftware.fiveoclocksomewhere.repos

import com.wreckingballsoftware.fiveoclocksomewhere.database.DBCocktails
import com.wreckingballsoftware.fiveoclocksomewhere.database.DBPlaces
import com.wreckingballsoftware.fiveoclocksomewhere.database.RegionalCocktailsDao
import com.wreckingballsoftware.fiveoclocksomewhere.network.CocktailDBService
import com.wreckingballsoftware.fiveoclocksomewhere.network.NetworkResponse
import com.wreckingballsoftware.fiveoclocksomewhere.network.models.ApiCocktails
import com.wreckingballsoftware.fiveoclocksomewhere.network.toNetworkErrorResponse
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.Response
import com.wreckingballsoftware.fiveoclocksomewhere.repos.models.UICocktail
import com.wreckingballsoftware.fiveoclocksomewhere.utils.getArticle
import com.wreckingballsoftware.fiveoclocksomewhere.utils.rand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CocktailsRepo(
    private val cocktailDBService: CocktailDBService,
    private val regionalCocktailsDao: RegionalCocktailsDao
    ) {
    suspend fun getCocktailById(cocktailId: Long): Response<UICocktail> = withContext(Dispatchers.IO) {
        val cocktails = callCocktailApi {
            cocktailDBService.getCocktailById(cocktailId.toString())
        }
        cocktails.toResponse()
    }

    suspend fun getRandomCocktail(): Response<UICocktail> = withContext(Dispatchers.IO) {
        val cocktails = callCocktailApi {
            cocktailDBService.getRandomCocktail()
        }
        cocktails.toResponse()
    }

    private suspend fun callCocktailApi(
        callForCocktail: (suspend () -> ApiCocktails)
    ): NetworkResponse<ApiCocktails> =
        withContext(Dispatchers.IO) {
            try {
                NetworkResponse.Success(callForCocktail())
            } catch (ex: HttpException) {
                ex.toNetworkErrorResponse()
            } catch (ex: Exception) {
                NetworkResponse.Error.UnknownNetworkError(ex)
            }
        }

    suspend fun getCocktailFromWhereIts5OClock(place: DBPlaces): Response<UICocktail> = withContext(Dispatchers.IO) {
        val regionalCocktails = regionalCocktailsDao.getAllCocktailsInPlace(place.id)
        val cocktail = chooseCocktail(regionalCocktails)
        getCocktailInfo(cocktail)
    }

    private suspend fun getCocktailInfo(dbCocktail: DBCocktails): Response<UICocktail> {
        val cocktail = callCocktailApiForDrink(dbCocktail.searchString)
        return cocktail.toResponse()
    }

    private suspend fun callCocktailApiForDrink(cocktail: String): NetworkResponse<ApiCocktails> =
        withContext(Dispatchers.IO) {
        try {
            NetworkResponse.Success(cocktailDBService.getCocktail(cocktail))
        } catch (ex: HttpException) {
            ex.toNetworkErrorResponse()
        } catch (ex: Exception) {
            NetworkResponse.Error.UnknownNetworkError(ex)
        }
    }

    private fun chooseCocktail(
        cocktails: List<DBCocktails>
    ): DBCocktails = cocktails[(0 .. cocktails.size).rand()]
}

private fun NetworkResponse<ApiCocktails>.toResponse(): Response<UICocktail> =
    when (this) {
        is NetworkResponse.Success -> {
            if (!data.drinks.isNullOrEmpty()) {
                val drink = data.drinks[0]
                Response.Success(
                    UICocktail(
                        id = drink.id,
                        name = drink.name,
                        displayName = "${drink.name.getArticle()} ${drink.name}",
                        glass = drink.glass,
                        instructions = drink.instructions,
                        imageUrl = drink.imageUrl,
                        ingredients = drink.getIngredientList(),
                        measures = drink.getMeasuresList(),
                        attribution = drink.attribution,
                    )
                )
            } else {
                Response.Error(errorMessage = "Unknown network error.")
            }
        }
        is NetworkResponse.Error -> {
            Response.Error(errorMessage = "Error code ${code}: ${exception.localizedMessage}")
        }
    }

