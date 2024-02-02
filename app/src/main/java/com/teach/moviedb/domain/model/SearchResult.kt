package com.teach.moviedb.domain.model

import com.teach.moviedb.data.dto.Movie

data class SearchResult(
    val movies: List<Movie>,
)
