package com.dicoding.the10wonders.presentation.screen.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dicoding.the10wonders.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "About") }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        model = R.drawable.ic_user_profile,
                        contentDescription = "Indra",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .rotate(270f)
                            .align(Alignment.CenterHorizontally)
                            .size(160.dp)
                            .clip(MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Indra",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "ichigoindra@gmail.com",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
