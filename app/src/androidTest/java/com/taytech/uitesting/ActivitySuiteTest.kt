package com.taytech.uitesting

import org.junit.runner.RunWith
import org.junit.runners.Suite

//Annotating that this class is a test suite
@RunWith(Suite::class)
@Suite.SuiteClasses(

        MainActivityTest::class,
        SecondActivityTest::class
)
class ActivitySuiteTest