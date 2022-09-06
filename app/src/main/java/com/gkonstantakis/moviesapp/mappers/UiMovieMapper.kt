package com.gkonstantakis.moviesapp.mappers

import com.gkonstantakis.moviesapp.models.UiMovie
import com.gkonstantakis.moviesapp.movies.models.Movie

class UiMovieMapper {
    fun mapFromEntity(entity: UiMovie): Movie {
        return Movie(
            id = entity.id,
            originalTitle = entity.originalTitle,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            genre = entity.genre
        )
    }

    fun mapToEntity(entity: Movie): UiMovie {
        return UiMovie(
            id = entity.id,
            originalTitle = entity.originalTitle,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            genre = entity.genre
        )
    }

    fun mapFromEntitiesList(entities: List<UiMovie>): List<Movie> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntitiesList(entities: List<Movie>): List<UiMovie> {
        return entities.map {
            mapToEntity(it)
        }
    }
}