package com.newsapp.multiplatform.android.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.newsapp.multiplatform.android.ui.screens.HomeScreen
import com.newsapp.multiplatform.android.ui.screens.SavedScreen
import com.newsapp.multiplatform.android.ui.screens.SettingsScreen
import com.newsapp.multiplatform.android.ui.viewmodels.HomeViewModel
import com.newsapp.multiplatform.android.ui.viewmodels.SavedViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) {
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(route = NavigationItem.Saved.route) {
            val viewModel = koinViewModel<SavedViewModel>()
            SavedScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(route = NavigationItem.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
