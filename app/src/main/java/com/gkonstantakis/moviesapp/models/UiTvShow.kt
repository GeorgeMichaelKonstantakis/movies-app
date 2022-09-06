package com.gkonstantakis.moviesapp.models

data class UiTvShow(
    var id: Int,
    var originalTitle: String?,
    var posterPath: String?,
    var firstAirDate: String?,
    var title: String?,
    var voteAverage: Number?,
    var overview: String?,
    var genre: String?
)
