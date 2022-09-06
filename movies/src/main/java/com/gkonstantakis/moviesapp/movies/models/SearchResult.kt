package com.gkonstantakis.moviesapp.movies.models

/**
 * Domain model for TvShow or Movie item when searching in the Search Screen.
 */
data class SearchResult(
    var id: Int?,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String?,
    var voteAverage: Number?,
    var isMovie: Boolean?
) {
}