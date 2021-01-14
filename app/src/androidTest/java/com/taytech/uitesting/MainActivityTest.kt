package com.taytech.uitesting

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test


@Suppress("DEPRECATION")
class MainActivityTest {

    @get:Rule
    val intentsRule = IntentsTestRule(MainActivity::class.java)

    //test to check camera intent. (must for intents)
    //and to verify the onActivityResult gets a image from camera and displays it in imageView
    @Test
    fun test_cameraIntent_isBitmapSetToImageView() {

        //create a mock activityResult object
        val activityResult = createImageCaptureActivityStub()

        //creating a mock intent compare its result with the mock activityResult object
        val expectedIntent: Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)

        //launching the fake intent for response
        intending(expectedIntent)
                .respondWith(activityResult)

        //making sure that image view has no image set before intent launch
        //using CUSTOM MATCHER
        onView(withId(R.id.image))
                .check(matches(not(ImageViewDrawableMatcher.hasDrawable())))

        //simulating a click on launch camera intent button
        onView(withId(R.id.button_launch_camera))
                .perform(click())

        //verifying the intent launch after button click
        intending(expectedIntent)

        //now making sure that image view has image set from camera intent
        //using CUSTOM MATCHER
        onView(withId(R.id.image))
                .check(matches(ImageViewDrawableMatcher.hasDrawable()))
    }

    //function to mock a activityResult object with dummy data
    private fun createImageCaptureActivityStub(): Instrumentation.ActivityResult {

        //creating the bundle to store expected intent data
        val bundle = Bundle()
        bundle.putParcelable(

                KEY_IMAGE_DATA,
                BitmapFactory.decodeResource(

                        intentsRule.activity.resources,
                        R.drawable.ic_launcher_background
                )
        )

        //creating an mock intent with our dummy camera data
        val resultData = Intent()
        resultData.putExtras(bundle)

        //return the mock activityResult object
        return Instrumentation.ActivityResult(RESULT_OK, resultData)
    }
}