package com.gkonstantakis.moviesapp.mappers

import com.gkonstantakis.moviesapp.models.UiSearchResult
import com.gkonstantakis.moviesapp.movies.models.SearchResult

class UiSearchResultMapper {
    fun mapFromEntity(entity: UiSearchResult): SearchResult {
        return SearchResult(
            id = entity.id,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            isMovie = entity.isMovie
        )
    }

    fun mapToEntity(entity: SearchResult): UiSearchResult {
        return UiSearchResult(
            id = entity.id,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            voteAverage = entity.voteAverage,
            isMovie = entity.isMovie
        )
    }

    fun mapFromEntitiesList(entities: List<UiSearchResult>): List<SearchResult> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntitiesList(entities: List<SearchResult>): List<UiSearchResult> {
        return entities.map {
            mapToEntity(it)
        }
    }
}