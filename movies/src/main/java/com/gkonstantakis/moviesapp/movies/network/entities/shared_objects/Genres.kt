package com.gkonstantakis.moviesapp.movies.network.entities.shared_objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String
) {
}