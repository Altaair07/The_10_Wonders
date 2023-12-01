package com.the10wonders

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.the10wonders.domain.model.DataWonder
import com.dicoding.the10wonders.presentation.MainActivity
import com.dicoding.the10wonders.presentation.The10WondersApp
import com.dicoding.the10wonders.presentation.navigation.Screen
import com.dicoding.the10wonders.presentation.ui.theme.The10WondersTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class The10WondersAppKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            The10WondersTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                The10WondersApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        composeTestRule.run {
            onNodeWithContentDescription("Splash Icon").assertIsDisplayed()
            navController.assertCurrentRouteName(Screen.Splash.route)
        }
    }

    @Test
    fun navHost_clickItem_navigateToDetailWithData() {
        composeTestRule.run {
            waitUntil(timeoutMillis = 3000L) {
                composeTestRule
                    .onAllNodesWithText("Home")
                    .fetchSemanticsNodes().size == 1

            }
            onNodeWithTag("lazy_list").performScrollToIndex(5)
            onNodeWithText(DataWonder.wonders[6].title).performClick()
            navController.assertCurrentRouteName(Screen.Detail.route)
            onNodeWithText(DataWonder.wonders[6].title).assertIsDisplayed()
        }
    }

    @Test
    fun navHost_bottomNavigation_Working() {
        composeTestRule.run {
            waitUntil(timeoutMillis = 3000L) {
                composeTestRule
                    .onAllNodesWithText("Home")
                    .fetchSemanticsNodes().size == 1

            }
            onNodeWithText("About").performClick()
            navController.assertCurrentRouteName(Screen.About.route)
            onNodeWithText("Home").performClick()
            navController.assertCurrentRouteName(Screen.Home.route)
        }
    }

    @Test
    fun navHost_clickAboutMenu_shouldShowAboutMePage() {
        composeTestRule.run {
            waitUntil(timeoutMillis = 3000L) {
                composeTestRule
                    .onAllNodesWithText("Home")
                    .fetchSemanticsNodes().size == 1

            }
            onNodeWithText("About").performClick()
            navController.assertCurrentRouteName(Screen.About.route)
            onNodeWithText("Indra").assertIsDisplayed()
            onNodeWithText("ichigoindra@gmail.com").assertIsDisplayed()
        }
    }
}