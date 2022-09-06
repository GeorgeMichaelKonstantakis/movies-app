package com.gkonstantakis.moviesapp.movies.repositories

import com.gkonstantakis.moviesapp.movies.mapping.NetworkSearchItemMapper
import com.gkonstantakis.moviesapp.movies.models.SearchResult
import com.gkonstantakis.moviesapp.movies.network.MovieNetworkService
import com.gkonstantakis.moviesapp.movies.network.entities.search_entity.inner_objects.Result
import com.gkonstantakis.moviesapp.movies.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository(
    private val movieNetworkService: MovieNetworkService,
    private val networkSearchItemMapper: NetworkSearchItemMapper
) {

    suspend fun getSearchResults(query: String, page: Int): Flow<DataState<List<SearchResult>>> =
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
                emit(DataState.SuccessNetworkSearch(searchResults))
            } catch (e: Exception) {
                // emit(DataState.Error(e))
            }
        }

}