package com.gkonstantakis.moviesapp.movies.mapping

import com.gkonstantakis.moviesapp.movies.models.Movie
import com.gkonstantakis.moviesapp.movies.network.entities.movie_entity.MovieNetworkEntity

class NetworkMovieMapper {

    fun mapFromEntity(entity: MovieNetworkEntity): Movie {
        return Movie(
            id = entity.id,
            originalTitle = entity.originalTitle,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            genre = entity.genres?.get(0)?.name
        )
    }
}