package com.gkonstantakis.moviesapp.movies.network.entities.shared_objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCountries(
    @SerializedName("iso_3166_1")
    @Expose
    var iso_3166_1: String,


    @SerializedName("name")
    @Expose
    var name: String
) {
}