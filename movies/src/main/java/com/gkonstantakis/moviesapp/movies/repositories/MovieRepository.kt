package com.gkonstantakis.moviesapp.movies.repositories

import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.models.SearchResult
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.models.TvShow
import com.gkonstantakis.moviesapp.movies.state.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getSearchResults(query: String, page: Int): Flow<DataState<List<SearchResult>>>

    suspend fun getMovieById(id: Int): Flow<DataState<List<Movie>>>

    suspend fun getTvShowById(id: Int): Flow<DataState<List<TvShow>>>

    suspend fun getMovieTrailersById(id: Int): Flow<DataState<List<Trailer>>>

    suspend fun getTvShowTrailersById(id: Int): Flow<DataState<List<Trailer>>>
}