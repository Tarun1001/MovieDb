package com.teach.moviedb.domain.model

import com.squareup.moshi.Json
import com.teach.moviedb.data.dto.Rating

data class MovieData(
    val actors: String,
    val awards: String,
    val boxOffice: String,
    val country: String,
    val director: String,
    val genre: String,


    val imdbRating: String,



    val language: String,



    val plot: String,

    val poster: String,

    val production: String,

    val rated: String,

    val ratings: List<Rating>,

    val released: String,


    val runtime: String,

    val title: String,

    val writer: String,

    val year: String
)
