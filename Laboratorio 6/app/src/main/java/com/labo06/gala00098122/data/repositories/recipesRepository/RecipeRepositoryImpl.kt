package com.labo06.gala00098122.data.repositories.recipesRepository

import com.labo06.gala00098122.data.api.KtorClient
import com.labo06.gala00098122.data.api.recipes.RecipesResponseDTO
import com.labo06.gala00098122.data.api.recipes.toModel
import com.labo06.gala00098122.data.model.Recipes
import io.ktor.client.call.body
import io.ktor.client.request.get

class RecipeRepositoryImpl : RecipeRepository {
  override suspend fun getRecipes(): Result<List<Recipes>> {
    try {
      val response: RecipesResponseDTO = KtorClient.client.get("search.php?s=") {
      
      }.body()
      return Result.success(response.results.map { recipesDTO -> recipesDTO.toModel() })
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}