package com.labo06.gala00098122.data.repositories.recipesRepository

import com.labo06.gala00098122.data.model.Recipes

interface RecipeRepository {
  suspend fun getRecipes(): Result<List<Recipes>>
}