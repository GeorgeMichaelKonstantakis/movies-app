package com.gkonstantakis.moviesapp.movies.models

/**
 * Domain model for trailers of movies and tvshows.
 */
data class Trailer(
    var key: String,
    var site: String,
    var id: String,
    var name: String
) {
}