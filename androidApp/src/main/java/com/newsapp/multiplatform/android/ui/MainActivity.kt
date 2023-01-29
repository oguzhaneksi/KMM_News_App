package com.newsapp.multiplatform.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.newsapp.multiplatform.android.ui.components.BottomNavBar
import com.newsapp.multiplatform.android.ui.navigation.Navigation
import com.newsapp.multiplatform.android.ui.navigation.NavigationItem
import com.newsapp.multiplatform.android.ui.themes.MyApplicationTheme

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            MyApplicationTheme(systemUiController = systemUiController) {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()

    val topLevelDestinations = listOf(
        NavigationItem.Home,
        NavigationItem.Saved,
        NavigationItem.Settings
    )

    val isTopLevelDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

    val backStackEntryState = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (isTopLevelDestination) {
                BottomNavBar(
                    navController = navController,
                    backStackEntryState = backStackEntryState,
                    bottomNavItems = topLevelDestinations
                )
            }
        }
    ) {
        Navigation(navController = navController)
    }
}
