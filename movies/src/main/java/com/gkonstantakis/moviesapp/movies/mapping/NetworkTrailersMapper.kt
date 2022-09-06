package com.gkonstantakis.moviesapp.movies.mapping

import com.gkonstantakis.moviesapp.movies.models.Trailer
import com.gkonstantakis.moviesapp.movies.network.entities.videos_entity.VideosEntity

class NetworkTrailersMapper {

    fun mapFromListOfEntities(entity: VideosEntity): List<Trailer> {
        val trailers = ArrayList<Trailer>()
        val results = entity.results
        if (!results.isNullOrEmpty()) {
            val result = results[0]
            trailers.add(
                Trailer(
                    id = result.id,
                    key = result.key,
                    site = result.site,
                    name = result.name
                )
            )
        }
        return trailers
    }
}