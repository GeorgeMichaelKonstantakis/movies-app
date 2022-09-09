package com.gkonstantakis.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkonstantakis.moviesapp.models.DetailsEventParams
import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.models.TvShow
import com.gkonstantakis.moviesapp.movies.repositories.MovieRepository
import com.gkonstantakis.moviesapp.movies.state.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieDataState: MutableLiveData<DataState<Movie>> = MutableLiveData()
    val movieDataState: LiveData<DataState<Movie>>
        get() = _movieDataState

    private val _tvShowDataState: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val tvShowDataState: LiveData<DataState<TvShow>>
        get() = _tvShowDataState

    private val _trailersDataState: MutableLiveData<DataState<List<Trailer>>> = MutableLiveData()
    val trailersDataState: LiveData<DataState<List<Trailer>>>
        get() = _trailersDataState

    fun setStateEvent(
        detailsStateEvent: DetailsStateEvent,
        detailsEventParams: DetailsEventParams?
    ) {
        viewModelScope.launch {
            when (detailsStateEvent) {
                is DetailsStateEvent.NetworkGetMovie -> {
                    movieRepository.getMovieById(detailsEventParams?.id!!)
                        .onEach { dataState ->
                            _movieDataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                is DetailsStateEvent.NetworkGetTvShow -> {
                    movieRepository.getTvShowById(detailsEventParams?.id!!)
                        .onEach { dataState ->
                            _tvShowDataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                is DetailsStateEvent.NetworkGetMovieTrailers -> {
                    movieRepository.getMovieTrailersById(detailsEventParams?.id!!)
                        .onEach { dataState ->
                            _trailersDataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                is DetailsStateEvent.NetworkGetTvShowTrailers -> {
                    movieRepository.getTvShowTrailersById(detailsEventParams?.id!!)
                        .onEach { dataState ->
                            _trailersDataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class DetailsStateEvent {
    object NetworkGetMovie : DetailsStateEvent()

    object NetworkGetTvShow : DetailsStateEvent()

    object NetworkGetMovieTrailers : DetailsStateEvent()

    object NetworkGetTvShowTrailers : DetailsStateEvent()
}