package com.gkonstantakis.moviesapp.movies.network.entities.tv_entity

import com.gkonstantakis.moviesapp.movies.network.entities.tv_entity.inner_objects.CreatedBy
import com.gkonstantakis.moviesapp.movies.network.entities.tv_entity.inner_objects.LastEpisodeToAir
import com.gkonstantakis.moviesapp.movies.network.entities.tv_entity.inner_objects.Networks
import com.gkonstantakis.moviesapp.movies.network.entities.tv_entity.inner_objects.Seasons
import com.gkonstantakis.moviesapp.movies.network.entities.shared_objects.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvNetworkEntity(

    @SerializedName("adult")
    @Expose
    var adult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String?,

    @SerializedName("genres")
    @Expose
    var genres: List<Genres>?,

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompanies>?,

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountries>?,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguages>?,

    @SerializedName("status")
    @Expose
    var status: String?,

    @SerializedName("tagline")
    @Expose
    var tagline: String?,

    @SerializedName("type")
    @Expose
    var type: String?,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Number?,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int?,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("created_by")
    @Expose
    var createdBy: List<CreatedBy>?,

    @SerializedName("episode_run_time")
    @Expose
    var episodeRunTime: List<Int>?,

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String?,

    @SerializedName("homepage")
    @Expose
    var homepage: String?,

    @SerializedName("in_production")
    @Expose
    var inProduction: Boolean?,

    @SerializedName("languages")
    @Expose
    var languages: List<String>?,

    @SerializedName("last_air_date")
    @Expose
    var lastAirDate: String?,

    @SerializedName("last_episode_to_air")
    @Expose
    var lastEpisodeToAir: LastEpisodeToAir?,

    @SerializedName("name")
    @Expose
    var name: String?,

    @SerializedName("next_episode_to_air")
    @Expose
    var nextEpisodeToAir: NextEpisodeToAir?,

    @SerializedName("networks")
    @Expose
    var networks: List<Networks>?,

    @SerializedName("number_of_episodes")
    @Expose
    var numberOfEpisodes: Int?,

    @SerializedName("number_of_seasons")
    @Expose
    var numberOfSeasons: Int?,

    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>?,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String?,

    @SerializedName("original_name")
    @Expose
    var originalName: String?,

    @SerializedName("popularity")
    @Expose
    var popularity: Number?,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String?,

    @SerializedName("seasons")
    @Expose
    var seasons: List<Seasons>?,

    @SerializedName("overview")
    @Expose
    var overview: String?
) {
}