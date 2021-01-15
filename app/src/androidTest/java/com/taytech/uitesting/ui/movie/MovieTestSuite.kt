package com.taytech.uitesting.ui.movie

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MovieDetailFragmentTest::class,
        DirectorsFragmentTest::class,
        StarActorsFragmentTest::class,
        MovieListFragmentTest::class
)
class MovieTestSuite