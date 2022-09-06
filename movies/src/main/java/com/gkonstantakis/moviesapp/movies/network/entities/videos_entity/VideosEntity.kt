package com.gkonstantakis.moviesapp.movies.network.entities.videos_entity

import com.gkonstantakis.moviesapp.movies.network.entities.videos_entity.inner_objects.Result
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideosEntity(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("results")
    @Expose
    var results: List<Result>
) {
}