package com.gkonstantakis.moviesapp.movies.network.entities.search_entity

import com.gkonstantakis.moviesapp.movies.network.entities.search_entity.inner_objects.Result
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchNetworkEntity(

    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("results")
    @Expose
    var results: List<Result>,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int?,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int?

) {
}