package ly.img.editor.matrix

import android.app.UiModeManager
import android.content.Context
import android.os.Looper
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class UiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun openAssetLibrary() {
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(testTag = "MainLoading"),
            timeoutMillis = 10_000,
        )

        composeTestRule
            .onNodeWithTag(testTag = "LibraryButton")
            .performClick()

        composeTestRule.onNodeWithTag(testTag = "LibraryNavigationBar")
            .onChildAt(0)
            .onChildAt(1) // Images tab
            .performClick()

        composeTestRule
            .waitUntilExactlyOneExists(hasTestTag(testTag = "LibrarySectionColumn"))

        composeTestRule.waitForIdle()
    }
}