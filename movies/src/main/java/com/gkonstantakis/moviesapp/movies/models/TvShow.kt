package com.gkonstantakis.moviesapp.movies.models

/**
 * Domain model for TvShow entity.
 */
data class TvShow(
    var id: Int,
    var originalTitle: String?,
    var posterPath: String?,
    var firstAirDate: String?,
    var title: String?,
    var voteAverage: Number?,
    var overview: String?,
    var genre: String?
) {
}