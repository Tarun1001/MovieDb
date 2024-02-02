package com.teach.moviedb.presentation.details_sc

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalMovies
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.teach.moviedb.presentation.MainViewModel
import com.teach.moviedb.presentation.details_sc.components.ErrorScreen
import com.teach.moviedb.presentation.details_sc.components.LoadingScreen
import com.teach.moviedb.presentation.details_sc.components.MovieData
import com.teach.moviedb.util.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(title: String, viewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true, block = { viewModel.getMovieData(title) })
    val movieDataResponse = viewModel.movieDataResponse.collectAsState().value
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = " Movie Database",
                    fontFamily = FontFamily.Serif,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon =
            {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.LocalMovies, contentDescription = null)

                }
            })

    }) {
        AnimatedContent(
            targetState = movieDataResponse,
            label = "animated_content",
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            transitionSpec = {
                fadeIn(animationSpec = tween(300, easing = LinearEasing)
                ) togetherWith fadeOut(animationSpec = tween(300, easing = LinearEasing))

            }
        ) {response->
            when(response){
                is Result.Success->{
                    val movieData=response.data
                    MovieData(movieData = movieData)
                }

                is Result.Error ->{
                    val errorMsg= response.error.localizedMessage?:"Something went Wrong"
                    ErrorScreen(movieTitle = title, errorMsg =errorMsg )
                }
                Result.Idle ->Unit
                Result.Loading -> {
                    LoadingScreen()
                }
            }


        }
    }

}