package com.gkonstantakis.moviesapp.movies.state

sealed class DataState<out R> {

    data class SuccessNetworkSearch<out T>(val data: T) : DataState<T>()

    data class SuccessNetworkMovie<out T>(val data: T) : DataState<T>()

    data class SuccessNetworkTvShow<out T>(val data: T) : DataState<T>()

    data class SuccessNetworkTrailers<out T>(val data: T) : DataState<T>()

    data class SuccessDatabaseGetWatchlist<out T>(val data: T) : DataState<T>()

    data class SuccessDatabaseSearchWatchlist<out T>(val data: T) : DataState<T>()

    data class SuccessDeleteMovieFromWatchlist<out T>(val data: T): DataState<T>()

    data class SuccessInsertMovieToWatchlist<out T>(val data: T): DataState<T>()

    data class Error(val exception: Exception) : DataState<Nothing>()

    object Loading : DataState<Nothing>()
}
