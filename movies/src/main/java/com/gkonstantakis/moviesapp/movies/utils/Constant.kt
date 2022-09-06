package com.gkonstantakis.moviesapp.movies.utils

class Constant {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
        const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch"
        const val API_KEY = "2810b46c0fe82e2e7eb43466581d495f"
        const val MIN_PAGE_NUM = 1
        const val MAX_PAGE_NUM = 1000

        fun youtubeTrailer(key: String): String {
            return "$YOUTUBE_VIDEO_URL?v=$key"
        }
    }
}