package com.taytech.uitesting

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

@Suppress("DEPRECATION")
class MainActivityTest {

    //when we are testing intents, this rule is necessary
    @get: Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    //test to check if the intent we are creating is sent to pick image from gallery
    //since this image will be loaded from the memory,
    //we cannot compare it with some sample image in our test.
    //that would be a different test.
    // ie checking whether a logo image is there as it should be (comparing)
    @Test
    fun test_validateIntentSentToPickImage() {

        //creating a mock intent similar to that in our main activity
        val expectedIntent: Matcher<Intent> = allOf(

                hasAction(Intent.ACTION_PICK),
                hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
//                hasExtra("key", 2) //optional if your intent has extras
        )

        //creating the mock activity result for gallery intent
        val activityResult = createGalleryPickActivityResultStub()

        //launching the mock intent for result
        //alternative: use intended to just start the intent without expecting result
        intending(expectedIntent).respondWith(activityResult)

        //verifying that the expected intent was launched at button click
        onView(withId(R.id.button_open_gallery)).perform(click())
        intended(expectedIntent)
    }

    //this method returns a mock of expected activity result of our gallery intent
    private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {

        //getting a sample image to test our intent
        //note: we are not comparing images.
        // we are just ensuring that the result would be a image URI
        //note: only get an image which is guaranteed to be present in the project
        // and the device where the tests would be run. otherwise test would fail

        //getting the context
        val resources: Resources = InstrumentationRegistry.getInstrumentation().context.resources

        //here we are getting the ic_launcher_background image
        //because it is guaranteed to be present in all projects
        val imageUri = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        resources.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                        resources.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                        resources.getResourceEntryName(R.drawable.ic_launcher_background))

        //create the mock intent with image uri
        val resultIntent = Intent()
        resultIntent.data = imageUri

        //returning a mock activity result
        return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
    }
}