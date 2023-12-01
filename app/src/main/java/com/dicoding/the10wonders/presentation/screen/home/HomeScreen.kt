package com.dicoding.the10wonders.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dicoding.the10wonders.domain.model.Wonder
import com.dicoding.the10wonders.presentation.composables.ItemWonder
import com.dicoding.the10wonders.presentation.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        wonders = state.wonders,
        onItemClick = {
            navController.navigate(Screen.Detail.createRoute(it.id))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    wonders: List<Wonder>,
    onItemClick: (Wonder) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "The 10 Wonders") },
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding).testTag("lazy_list"),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items = wonders, key = { it.id }) {
                ItemWonder(
                    title = it.title,
                    imageRes = it.imageRes,
                    onClick = { onItemClick(it) },
                )
            }
        }
    }
}