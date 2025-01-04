package dev.soul.moviedbkmp.android.presentation.navigation

import dev.soul.moviedbkmp.android.R

sealed class Screens(val route: String, val title: String, val icon: Int) {
    object Home : Screens("home", "Home", R.drawable.ic_home)
    object Profile : Screens("profile", "Profile", R.drawable.ic_profile)
    object Settings : Screens("settings", "Settings", R.drawable.ic_search)
    object Search : Screens("search", "Search", R.drawable.ic_search)
    object Details : Screens("details/{itemId}", "Details", R.drawable.ic_search){
        fun createRoute(itemId: Long) = "details/$itemId"
    }

}