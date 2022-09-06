package com.gkonstantakis.moviesapp.movies.network.entities.search_entity.inner_objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KnownFor(
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String?,

    @SerializedName("overview")
    @Expose
    var overview: String?,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String?,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String?,

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int?>?,

    @SerializedName("id")
    @Expose
    var id: Int?,

    @SerializedName("media_type")
    @Expose
    var mediaType: String?,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String?,

    @SerializedName("title")
    @Expose
    var title: String?,

    @SerializedName("popularity")
    @Expose
    var popularity: Number?,

    @SerializedName("video")
    @Expose
    var video: Boolean?,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Number?,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int?,

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String?,

    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String?>?,

    @SerializedName("original_name")
    @Expose
    var originalName: String?
) {
}