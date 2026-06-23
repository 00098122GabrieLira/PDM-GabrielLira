package com.labo06.gala00098122.data.api.recipes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesDTO(
  @SerialName("idMeal") val id: Int,
  @SerialName("strMeal") val name: String,
  @SerialName("strCategory") val category: String,
  @SerialName("strCountry") val country: String,
  @SerialName("strMealThumb") val imageUrl: String
  )