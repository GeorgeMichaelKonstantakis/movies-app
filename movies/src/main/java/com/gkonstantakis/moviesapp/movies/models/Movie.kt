package com.gkonstantakis.moviesapp.movies.models

/**
 * Domain model for Movie entity.
 */
data class Movie(
    var id: Int,
    var originalTitle: String,
    var posterPath: String?,
    var releaseDate: String,
    var title: String,
    var voteAverage: Number,
    var overview: String,
    var genre: String
) {
}