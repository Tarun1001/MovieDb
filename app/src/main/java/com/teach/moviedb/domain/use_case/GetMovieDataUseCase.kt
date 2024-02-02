package com.teach.moviedb.domain.use_case

import com.teach.moviedb.data.dto.MovieDataDto
import com.teach.moviedb.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDataUseCase @Inject constructor(private val repository: RemoteDataRepository) {

    suspend operator  fun invoke(title:String): Flow<MovieDataDto> = flow {
        emit(repository.getMovieData(title))
    }
}