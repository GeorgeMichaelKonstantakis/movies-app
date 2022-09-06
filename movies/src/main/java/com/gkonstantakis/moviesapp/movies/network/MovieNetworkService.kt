package com.gkonstantakis.moviesapp.movies.network

import com.gkonstantakis.moviesapp.movies.network.entities.movie_entity.MovieNetworkEntity
import com.gkonstantakis.moviesapp.movies.network.entities.search_entity.SearchNetworkEntity
import com.gkonstantakis.moviesapp.movies.network.entities.tv_entity.TvNetworkEntity
import com.gkonstantakis.moviesapp.movies.network.entities.videos_entity.VideosEntity
import com.gkonstantakis.moviesapp.movies.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieNetworkService {

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = Constant.API_KEY
    ): MovieNetworkEntity

    @GET("tv/{id}")
    suspend fun getTvShowById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = Constant.API_KEY
    ): TvNetworkEntity

    @GET("search/multi")
    suspend fun getMoviesAndTvShowsBySearch(
        @Query("api_key") api_key: String = Constant.API_KEY,
        @Query("include_adult") adult: Boolean = false,
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchNetworkEntity

    @GET("movie/{id}/videos")
    suspend fun getMovieTrailersById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = Constant.API_KEY
    ): VideosEntity

    @GET("tv/{id}/videos")
    suspend fun getTvShowTrailersById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = Constant.API_KEY
    ): VideosEntity
}