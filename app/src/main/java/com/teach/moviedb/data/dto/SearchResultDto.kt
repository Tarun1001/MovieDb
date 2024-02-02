package com.teach.moviedb.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.teach.moviedb.domain.model.SearchResult

@JsonClass(generateAdapter = true)
data class SearchResultDto(
    @Json(name = "Response")
    val response: String,
    @Json(name = "Search")
    val search: List<Movie>,
    @Json(name = "totalResults")
    val totalResults: String
)
fun SearchResultDto.toSearchResult():SearchResult{
    return SearchResult(movies=search)
}