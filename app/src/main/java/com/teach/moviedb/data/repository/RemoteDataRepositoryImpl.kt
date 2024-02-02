package com.teach.moviedb.data.repository

import com.teach.moviedb.data.MovieApi
import com.teach.moviedb.data.dto.MovieDataDto
import com.teach.moviedb.data.dto.SearchResultDto
import com.teach.moviedb.domain.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(private val api:MovieApi): RemoteDataRepository{
    override suspend fun getMovieData(title: String): MovieDataDto {
        return withContext(Dispatchers.Default){
            api.getMovieData(title=title)
        }
    }

    override suspend fun getSearchResult(search: String): SearchResultDto {
        return withContext(Dispatchers.Default){
            api.getSearchResult(search=search)
        }
    }

}