package com.newsapp.multiplatform.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.newsapp.multiplatform.android.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {

    object Home : NavigationItem("home", R.string.title_home, R.drawable.ic_home)
    object Saved : NavigationItem("saved", R.string.title_saved, R.drawable.ic_saved)
    object Settings : NavigationItem("settings", R.string.title_settings, R.drawable.ic_settings)
}
