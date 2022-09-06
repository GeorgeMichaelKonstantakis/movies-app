package com.gkonstantakis.moviesapp.movies.network.entities.movie_entity

import com.gkonstantakis.moviesapp.movies.network.entities.shared_objects.Genres
import com.gkonstantakis.moviesapp.movies.network.entities.shared_objects.ProductionCompanies
import com.gkonstantakis.moviesapp.movies.network.entities.shared_objects.ProductionCountries
import com.gkonstantakis.moviesapp.movies.network.entities.shared_objects.SpokenLanguages
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieNetworkEntity(
    @SerializedName("adult")
    @Expose
    var adult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String?,

    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Object?,

    @SerializedName("budget")
    @Expose
    var budget: Int,

    @SerializedName("genres")
    @Expose
    var genres: List<Genres>,

    @SerializedName("homepage")
    @Expose
    var homepage: String?,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("imdb_id")
    @Expose
    var imdbId: String?,


    @SerializedName("original_language")
    @Expose
    var originalLanguage: String,


    @SerializedName("original_title")
    @Expose
    var originalTitle: String,


    @SerializedName("overview")
    @Expose
    var overview: String,


    @SerializedName("popularity")
    @Expose
    var popularity: Number,


    @SerializedName("poster_path")
    @Expose
    var posterPath: String?,

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompanies>,

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountries>,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String,

    @SerializedName("revenue")
    @Expose
    var revenue: Int,

    @SerializedName("runtime")
    @Expose
    var runtime: Int?,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguages>,

    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("tagline")
    @Expose
    var tagline: String?,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("video")
    @Expose
    var video: Boolean,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Number,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int
) {
}