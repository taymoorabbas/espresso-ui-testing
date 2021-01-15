package com.taytech.uitesting.data.source

import com.taytech.uitesting.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}