package com.gkonstantakis.moviesapp.movies.network.entities.shared_objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompanies(
    @SerializedName("name")
    @Expose
    var name: String,


    @SerializedName("id")
    @Expose
    var id: Int,


    @SerializedName("logo_path")
    @Expose
    var logoPath: String?,


    @SerializedName("origin_country")
    @Expose
    var originCountry: String
) {
}