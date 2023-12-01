package com.dicoding.the10wonders.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dicoding.the10wonders.domain.model.Wonder

@Composable
fun DetailScreen(
    id: Int?,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = id) {
        viewModel.onEvent(DetailEvent.OnGetDetail(id ?: 0))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailContent(
        wonder = state.wonder,
        navigateUp = { navController.navigateUp() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    wonder: Wonder?,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${wonder?.title}") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .testTag("scroll")
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = wonder?.imageRes,
                contentDescription = "Image ${wonder?.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .height(256.dp)
            )
            Text(
                text = wonder?.description.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 28.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lokasi",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            AsyncImage(
                model = wonder?.wonderMap,
                contentDescription = "Maps",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}