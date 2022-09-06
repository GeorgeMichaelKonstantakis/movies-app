package com.gkonstantakis.moviesapp.movies.network.entities.videos_entity.inner_objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1: String,

    @SerializedName("iso_3166_1")
    @Expose
    var iso_3166_1: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("key")
    @Expose
    var key: String,

    @SerializedName("site")
    @Expose
    var site: String,

    @SerializedName("size")
    @Expose
    var size: Int,

    @SerializedName("type")
    @Expose
    var type: String,

    @SerializedName("official")
    @Expose
    var official: Boolean,

    @SerializedName("published_at")
    @Expose
    var publishedAt: String,

    @SerializedName("id")
    @Expose
    var id: String
) {
}