package com.gkonstantakis.moviesapp

import android.app.Application
import com.gkonstantakis.moviesapp.movies.mapping.NetworkMovieMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkSearchItemMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkTrailersMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkTvShowMapper
import com.gkonstantakis.moviesapp.movies.network.MovieNetworkService
import com.gkonstantakis.moviesapp.movies.repositories.MovieRepository
import com.gkonstantakis.moviesapp.movies.repositories.MovieRepositoryImpl
import com.gkonstantakis.moviesapp.movies.utils.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApplication : Application() {

    lateinit var moviesNetworkService: MovieNetworkService
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        moviesNetworkService =
            provideGsonBuilder().let {
                provideNetwork(it).build().create(MovieNetworkService::class.java)
            }
        movieRepository =
            moviesNetworkService.let {
                MovieRepositoryImpl(
                    moviesNetworkService,
                    NetworkSearchItemMapper(),
                    NetworkMovieMapper(),
                    NetworkTvShowMapper(),
                    NetworkTrailersMapper()
                )
            }
    }

    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    fun provideNetwork(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }
}