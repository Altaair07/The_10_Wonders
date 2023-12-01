package com.dicoding.the10wonders.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ItemWonder(
    title: String,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = imageRes,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 160.dp, height = 144.dp)
                    .padding(end = 16.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}