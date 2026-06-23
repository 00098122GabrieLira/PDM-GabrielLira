package com.labo06.gala00098122.data.api.recipes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponseDTO(
  @SerialName("meals") val results: List<RecipesDTO>
)