package com.gkonstantakis.moviesapp.movies.network.entities.shared_objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpokenLanguages(

    @SerializedName("english_name")
    @Expose
    var englishName: String?,

    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1: String,


    @SerializedName("name")
    @Expose
    var name: String
) {
}