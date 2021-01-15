package com.taytech.uitesting.util

import androidx.test.espresso.idling.CountingIdlingResource

object MyEspressoIdlingResource {

    //giving a name for our idleResource object
    private const val RESOURCE = "GLOBAL"

//    jvmField=Instructs the Kotlin compiler not to generate getters/setters for this property
//    and expose it as a field.
    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {

        //if idle counter is != 0
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}
