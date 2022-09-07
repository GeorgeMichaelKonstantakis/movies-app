package com.gkonstantakis.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkonstantakis.moviesapp.models.SearchEventParams
import com.gkonstantakis.moviesapp.movies.models.SearchResult
import com.gkonstantakis.moviesapp.movies.repositories.MovieRepository
import com.gkonstantakis.moviesapp.movies.state.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<SearchResult>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<SearchResult>>>
        get() = _dataState

    fun setStateEvent(searchStateEvent: SearchStateEvent, params: SearchEventParams) {
        viewModelScope.launch {
            when (searchStateEvent) {
                is SearchStateEvent.GetNetworkProducts -> {
                    movieRepository.getSearchResults(params.query!!, params.page!!)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class SearchStateEvent {
    object GetNetworkProducts : SearchStateEvent()
}