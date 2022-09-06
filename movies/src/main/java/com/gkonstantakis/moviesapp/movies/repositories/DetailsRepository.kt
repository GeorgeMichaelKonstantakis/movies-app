package com.gkonstantakis.moviesapp.movies.repositories

import com.gkonstantakis.moviesapp.movies.mapping.NetworkMovieMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkTrailersMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkTvShowMapper
import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.models.TvShow
import com.gkonstantakis.moviesapp.movies.network.MovieNetworkService
import com.gkonstantakis.moviesapp.movies.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Exception

class DetailsRepository(
    private val movieNetworkService: MovieNetworkService,
    private val networkMovieMapper: NetworkMovieMapper,
    private val networkTvShowMapper: NetworkTvShowMapper,
    private val networkTrailersMapper: NetworkTrailersMapper
) {

    suspend fun getMovieById(id: Int): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.Loading)
        try {
            val movies: ArrayList<Movie> = ArrayList()
            val networkMovie = movieNetworkService.getMovieById(id)
            val movie = networkMovieMapper.mapFromEntity(networkMovie)
            movies.add(movie)
            emit(DataState.SuccessNetworkMovie(movies))
        } catch (e: Exception) {
        }
    }

    suspend fun getTvShowById(id: Int): Flow<DataState<List<TvShow>>> = flow {
        emit(DataState.Loading)
        try {
            val tvShows: ArrayList<TvShow> = ArrayList()
            val networkTvShow = movieNetworkService.getTvShowById(id)
            val tvShow = networkTvShowMapper.mapFromEntity(networkTvShow)
            tvShows.add(tvShow)
            emit(DataState.SuccessNetworkTvShow(tvShows))
        } catch (e: Exception) {
        }
    }

    suspend fun getMovieTrailersById(id: Int): Flow<DataState<List<Trailer>>> = flow {
        emit(DataState.Loading)
        try {
            val networkTrailers = movieNetworkService.getMovieTrailersById(id)
            val trailers = networkTrailersMapper.mapFromListOfEntities(networkTrailers)
            emit(DataState.SuccessNetworkTrailers(trailers))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getTvShowTrailersById(id: Int): Flow<DataState<List<Trailer>>> = flow {
        emit(DataState.Loading)
        try {
            val networkTrailers = movieNetworkService.getTvShowTrailersById(id)

            val trailers = networkTrailersMapper.mapFromListOfEntities(networkTrailers)
            emit(DataState.SuccessNetworkTrailers(trailers))
        } catch (e: Exception) {
            // emit(DataState.Error(e))
        }
    }
}