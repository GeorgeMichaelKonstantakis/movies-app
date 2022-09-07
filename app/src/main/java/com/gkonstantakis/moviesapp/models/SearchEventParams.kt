package com.gkonstantakis.moviesapp.models

data class SearchEventParams(
    var query: String?,
    var page: Int?
) {
}