package com.taytech.uitesting

import android.content.Intent
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test


@Suppress("DEPRECATION")
class WebViewActivityTest {

    //test data
    private val MACCHIATO = "Macchiato"
    private val DOPPIO = "Doppio"

    @get:Rule
    val mActivityRule: ActivityTestRule<WebViewActivity> =
            ActivityTestRule(WebViewActivity::class.java)

    //test to check when user types in input text and click submit button
    @Test
    fun typeTextInInput_clickButton_SubmitsForm() {

        //launch the main activity
        mActivityRule.launchActivity(withWebFormIntent())

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView() // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(webKeys(MACCHIATO))
                // Find the submit button
                .withElement(findElement(Locator.ID, "submitBtn"))
                // Simulate a click via javascript
                .perform(webClick())
                // Find the response element by ID
                .withElement(findElement(Locator.ID, "response"))
                // Verify that the response page contains the entered text
                .check(webMatches(getText(), containsString(MACCHIATO)))
    }

    //test to check when user types in input text and click change text button
    @Test
    fun typeTextInInput_clickButton_ChangesText() {
        // Lazily launch the Activity with a custom start Intent per test
        mActivityRule.launchActivity(withWebFormIntent())

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView() // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(webKeys(DOPPIO))
                // Find the change text button.
                .withElement(findElement(Locator.ID, "changeTextBtn"))
                // Click on it.
                .perform(webClick())
                // Find the message element by ID
                .withElement(findElement(Locator.ID, "message"))
                // Verify that the text is displayed
                .check(webMatches(getText(), containsString(DOPPIO)))
    }

    /**
     * @return start [Intent] for the simple web form URL.
     */
    private fun withWebFormIntent(): Intent {
        val basicFormIntent = Intent()
        basicFormIntent.putExtra(WebViewActivity.KEY_URL_TO_LOAD, WebViewActivity.WEB_FORM_URL)
        return basicFormIntent
    }
}