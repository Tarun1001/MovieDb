package com.teach.moviedb.domain.repository

import com.teach.moviedb.data.dto.MovieDataDto
import com.teach.moviedb.data.dto.SearchResultDto

interface RemoteDataRepository {

    suspend fun getMovieData(title:String):MovieDataDto

    suspend fun getSearchResult(search:String):SearchResultDto
}