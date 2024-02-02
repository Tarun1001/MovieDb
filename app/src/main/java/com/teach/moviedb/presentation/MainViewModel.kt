package com.teach.moviedb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teach.moviedb.data.dto.toMovieData
import com.teach.moviedb.data.dto.toSearchResult
import com.teach.moviedb.domain.model.MovieData
import com.teach.moviedb.domain.model.SearchResult
import com.teach.moviedb.domain.use_case.UseCases
import com.teach.moviedb.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCases: UseCases):ViewModel() {
    private val _movieDataResponse = MutableStateFlow<Result<MovieData>>(Result.Idle)
    val movieDataResponse=_movieDataResponse.asStateFlow()

    private val _searchResponse = MutableStateFlow<Result<SearchResult>>(Result.Idle)
    val searchResponse=_searchResponse.asStateFlow()

    fun getMovieData(title:String)= viewModelScope.launch {
        useCases.getMovieDataUseCase(title).onStart { _movieDataResponse.value=Result.Loading }
            .catch { _movieDataResponse.value=Result.Error(it) }
            .collect{
                val result = it.toMovieData()
                _movieDataResponse.value=Result.Success(result)
            }
    }
    fun getSearchResult(search:String)= viewModelScope.launch {
        useCases.getSearchResultUseCase(search).onStart { _searchResponse.value=Result.Loading }
            .catch { _searchResponse.value=Result.Error(it) }
            .collect{
                val result = it.toSearchResult()
                _searchResponse.value=Result.Success(result)
            }
    }
}