package com.the10wonders.presentation.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.dicoding.the10wonders.domain.model.DataWonder
import com.dicoding.the10wonders.domain.model.Wonder
import com.dicoding.the10wonders.presentation.screen.detail.DetailContent
import com.dicoding.the10wonders.presentation.ui.theme.The10WondersTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeWonders: Wonder
        get() = DataWonder.wonders[0]

    @Before
    fun setUp() {
        composeTestRule.setContent {
            The10WondersTheme {
                DetailContent(
                    wonder = fakeWonders,
                    navigateUp = {}
                )
            }
        }
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.run {
            onNodeWithText(fakeWonders.title).assertIsDisplayed()
            onNodeWithText(fakeWonders.description).assertIsDisplayed()
            onNodeWithContentDescription("Image ${fakeWonders.title}").assertIsDisplayed()
        }
    }

    @Test
    fun detailContent_isScrollable() {
        composeTestRule.onNodeWithTag("scroll").performTouchInput {
            swipeUp()
        }
    }
}