package com.dicoding.the10wonders.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dicoding.the10wonders.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    onTimeOut: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)

    LaunchedEffect(Unit) {
        delay(2.5.seconds)
        currentOnTimeOut()
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
            contentDescription = "Splash Icon",
            modifier = Modifier.size(180.dp)
                .align(Alignment.Center)
        )
    }
}