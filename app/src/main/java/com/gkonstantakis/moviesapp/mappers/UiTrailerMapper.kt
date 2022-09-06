package com.gkonstantakis.moviesapp.mappers

import com.gkonstantakis.moviesapp.models.UiTrailer
import com.gkonstantakis.moviesapp.movies.models.Trailer

class UiTrailerMapper {
    fun mapFromEntity(entity: UiTrailer): Trailer {
        return Trailer(
            id = entity.id,
            key = entity.key,
            name = entity.name,
            site = entity.site
        )
    }

    fun mapToEntity(entity: Trailer): UiTrailer {
        return UiTrailer(
            id = entity.id,
            key = entity.key,
            name = entity.name,
            site = entity.site
        )
    }

    fun mapFromEntitiesList(entities: List<UiTrailer>): List<Trailer> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntitiesList(entities: List<Trailer>): List<UiTrailer> {
        return entities.map {
            mapToEntity(it)
        }
    }
}