package com.gkonstantakis.moviesapp.models

data class UiMovie(
    var id: Int,
    var originalTitle: String,
    var posterPath: String?,
    var releaseDate: String,
    var title: String,
    var voteAverage: Number,
    var overview: String,
    var genre: String
)
