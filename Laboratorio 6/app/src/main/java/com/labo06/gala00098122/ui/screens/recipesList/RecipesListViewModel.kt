package com.labo06.gala00098122.ui.screens.recipesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labo06.gala00098122.data.model.Recipes
import com.labo06.gala00098122.data.repositories.recipesRepository.RecipeRepository
import com.labo06.gala00098122.data.repositories.recipesRepository.RecipeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipesListViewModel: ViewModel() {
  private val recipeRepository: RecipeRepository = RecipeRepositoryImpl()
  
  private val _recipes = MutableStateFlow<List<Recipes>>(emptyList())
  val recipes = _recipes.asStateFlow()
  
  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  
  private val _refresh = MutableStateFlow<Boolean>(false)
  val refresh = _refresh.asStateFlow()
  
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()
  
  init {
    loadRecipes()
  }
  
  fun loadRecipes() {
    viewModelScope.launch {
      _error.value = null
      _loading.value = true
      
      recipeRepository.getRecipes()
        .onSuccess { recipes ->
          _recipes.value = recipes
          
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al cargar las recetas. Por favor, intenta recargar la página."
        }
      
      _loading.value = false
    }
  }
  
  fun refreshRecipes() {
    viewModelScope.launch {
      _error.value = null
      _refresh.value = true
      
      recipeRepository.getRecipes()
        .onSuccess { recipes ->
          _recipes.value = recipes
          
        }
        .onFailure { error ->
          _error.value =
            "Ocurrió un error al recargar las recetas. Por favor, intenta recargar la página."
        }
      
      _refresh.value = false
    }
  }
}