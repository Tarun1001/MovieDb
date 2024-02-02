package com.teach.moviedb.presentation.navigation

import android.telecom.Call.Details
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.teach.moviedb.di.AppModule
import com.teach.moviedb.domain.use_case.UseCases
import com.teach.moviedb.presentation.MainViewModel
import com.teach.moviedb.presentation.details_sc.DetailsScreen
import com.teach.moviedb.presentation.search_sc.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SearchScreen.name) {
        composable(route = Screen.SearchScreen.name) {
            SearchScreen(onItemClick = { title -> navController.navigate(route = "${Screen.DetailsScreen.name}/$title") },

            )
        }
        composable(
            route = "${Screen.DetailsScreen.name}/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            }),
            enterTransition =   {
                slideInHorizontally  (initialOffsetX = {-it},
                    animationSpec = tween(300, easing =  FastOutLinearInEasing)
                )

            },
            exitTransition =   {
                slideOutHorizontally  (targetOffsetX = {-it},
                    animationSpec = tween(300, easing = FastOutLinearInEasing)
                )

            }
        ) {navbackStackEntry->
            navbackStackEntry.  arguments?.getString("title").let { title->
                DetailsScreen(title = title!!)
            }
            //SearchScreen(onItemClick = { title -> navController.navigate(route = "${Screen.DetailsScreen.name}/$title") })
        }
    }

}