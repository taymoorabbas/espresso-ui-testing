package com.taytech.uitesting

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

//creating a custom matcher to check imageView has loaded drawable bitmap into it
object ImageViewDrawableMatcher {

    fun hasDrawable(): BoundedMatcher<View, ImageView> {

        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

            //this method is mainly used for debugging (like logcat)
            override fun describeTo(description: Description?) {
                description?.appendText("has drawable")
            }

            //actual logic for test
            //if the drawable in our image view is not null. test is passed
            override fun matchesSafely(item: ImageView?): Boolean {
                return item?.drawable != null
            }
        }
    }
}