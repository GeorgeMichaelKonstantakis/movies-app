package com.gkonstantakis.moviesapp.movies.repositories

import android.util.Log
import com.gkonstantakis.moviesapp.movies.mapping.NetworkMovieMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkSearchItemMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkTrailersMapper
import com.gkonstantakis.moviesapp.movies.mapping.NetworkTvShowMapper
import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.models.SearchResult
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.models.TvShow
import com.gkonstantakis.moviesapp.movies.network.MovieNetworkService
import com.gkonstantakis.moviesapp.movies.network.entities.search_entity.inner_objects.Result
import com.gkonstantakis.moviesapp.movies.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieNetworkService: MovieNetworkService,
    private val networkSearchItemMapper: NetworkSearchItemMapper,
    private val networkMovieMapper: NetworkMovieMapper,
    private val networkTvShowMapper: NetworkTvShowMapper,
    private val networkTrailersMapper: NetworkTrailersMapper
) : MovieRepository {

    override suspend fun getSearchResults(
        query: String,
        page: Int
    ): Flow<DataState<List<SearchResult>>> =
        flow {
            emit(DataState.Loading)
            try {
                val networkSearchResults = movieNetworkService.getMoviesAndTvShowsBySearch(
                    query = query,
                    page = page
                )
                val validSearchResults: ArrayList<Result> = ArrayList()
                networkSearchResults.results.forEach {
                    if (it.knownFor == null) {
                        validSearchResults.add(it)
                    }
                }
                val searchResults =
                    networkSearchItemMapper.mapFromListOfEntities(validSearchResults)
                emit(DataState.Success(searchResults))
            } catch (e: Exception) {
                Log.e("MovieRepositoryImpl", "getSearchResults: " + e.toString())
                emit(DataState.Error("SEARCH_RESULTS_ERROR"))
            }
        }

    override suspend fun getMovieById(id: Int): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.Loading)
        try {
            val movies: ArrayList<Movie> = ArrayList()
            val networkMovie = movieNetworkService.getMovieById(id)
            val movie = networkMovieMapper.mapFromEntity(networkMovie)
            movies.add(movie)
            emit(DataState.Success(movies))
        } catch (e: Exception) {
            Log.e("MovieRepositoryImpl", "getMovieById: " + e.toString())
            emit(DataState.Error("GET_MOVIE_ERROR"))
        }
    }

    override suspend fun getTvShowById(id: Int): Flow<DataState<List<TvShow>>> = flow {
        emit(DataState.Loading)
        try {
            val tvShows: ArrayList<TvShow> = ArrayList()
            val networkTvShow = movieNetworkService.getTvShowById(id)
            val tvShow = networkTvShowMapper.mapFromEntity(networkTvShow)
            tvShows.add(tvShow)
            emit(DataState.Success(tvShows))
        } catch (e: Exception) {
            Log.e("MovieRepositoryImpl", "getTvShowById: " + e.toString())
            emit(DataState.Error("GET_TV_SHOW_ERROR"))
        }
    }

    override suspend fun getMovieTrailersById(id: Int): Flow<DataState<List<Trailer>>> = flow {
        emit(DataState.Loading)
        try {
            val networkTrailers = movieNetworkService.getMovieTrailersById(id)
            val trailers = networkTrailersMapper.mapFromListOfEntities(networkTrailers)
            emit(DataState.Success(trailers))
        } catch (e: Exception) {
            Log.e("MovieRepositoryImpl", "getMovieTrailersById: " + e.toString())
            emit(DataState.Error("MOVIE_TRAILER_ERROR"))
        }
    }

    override suspend fun getTvShowTrailersById(id: Int): Flow<DataState<List<Trailer>>> = flow {
        emit(DataState.Loading)
        try {
            val networkTrailers = movieNetworkService.getTvShowTrailersById(id)

            val trailers = networkTrailersMapper.mapFromListOfEntities(networkTrailers)
            emit(DataState.Success(trailers))
        } catch (e: Exception) {
            Log.e("MovieRepositoryImpl", "getTvShowTrailersById: " + e.toString())
            emit(DataState.Error("TV_SHOW_TRAILER_ERROR"))
        }
    }
}