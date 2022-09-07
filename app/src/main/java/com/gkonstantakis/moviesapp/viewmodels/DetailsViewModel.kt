package com.gkonstantakis.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.repositories.MovieRepository
import com.gkonstantakis.moviesapp.movies.state.DataState

class DetailsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {


    private val _trailersDataState: MutableLiveData<DataState<List<Trailer>>> = MutableLiveData()
    val trailersDataState: LiveData<DataState<List<Trailer>>>
        get() = _trailersDataState

    fun setStateEvent() {

    }

}

sealed class DetailsStateEvent {
    object NetworkGetMovie : DetailsStateEvent()

    object NetworkGetTvShow : DetailsStateEvent()

    object NetworkGetMovieTrailers : DetailsStateEvent()

    object NetworkGetTvShowTrailers : DetailsStateEvent()
}