package com.labo06.gala00098122.data.api.recipes

import com.labo06.gala00098122.data.model.Recipes

fun RecipesDTO.toModel(): Recipes {
  return Recipes(
    id = id,
    name = name,
    country = country,
    category = category,
    imageUrl = imageUrl
  )
}