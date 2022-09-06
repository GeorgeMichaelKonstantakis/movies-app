package com.gkonstantakis.moviesapp.mappers

import com.gkonstantakis.moviesapp.models.UiTvShow
import com.gkonstantakis.moviesapp.movies.models.TvShow

class UiTvShowMapper {
    fun mapFromEntity(entity: UiTvShow): TvShow {
        return TvShow(
            id = entity.id,
            originalTitle = entity.originalTitle,
            posterPath = entity.posterPath,
            firstAirDate = entity.firstAirDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            genre = entity.genre
        )
    }

    fun mapToEntity(entity: TvShow): UiTvShow {
        return UiTvShow(
            id = entity.id,
            originalTitle = entity.originalTitle,
            posterPath = entity.posterPath,
            firstAirDate = entity.firstAirDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            overview = entity.overview,
            genre = entity.genre
        )
    }

    fun mapFromEntitiesList(entities: List<UiTvShow>): List<TvShow> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntitiesList(entities: List<TvShow>): List<UiTvShow> {
        return entities.map {
            mapToEntity(it)
        }
    }
}