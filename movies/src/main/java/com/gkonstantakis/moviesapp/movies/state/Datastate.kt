package com.gkonstantakis.moviesapp.movies.state

import android.app.appsearch.SearchResult
import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.models.TvShow

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()

    data class SuccessMovie(val movie: Movie) : DataState<Nothing>()

    data class SuccessTvShow(val tvShow: TvShow) : DataState<Nothing>()

    data class Error(val message: String) : DataState<Nothing>()

    object Loading : DataState<Nothing>()
}
