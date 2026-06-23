package com.labo06.gala00098122.ui.screens.recipesList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.labo06.gala00098122.data.model.Recipes

@Composable
fun RecipeItem(
  recipe: Recipes
) {
  
  Card(
    modifier = Modifier
      .fillMaxWidth(),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(12.dp)
  ) {
    Row(modifier = Modifier.padding(12.dp)) {
      AsyncImage(
        model = recipe.imageUrl,
        contentDescription = recipe.name,
        modifier = Modifier
          .size(width = 80.dp, height = 120.dp)
          .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
      )
      Spacer(modifier = Modifier.width(12.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text =recipe.name,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Pais: ${recipe.country}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Categoria: ${recipe.category}",
          style = MaterialTheme.typography.bodySmall,
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}