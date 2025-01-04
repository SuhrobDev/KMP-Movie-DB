package dev.soul.moviedbkmp.android.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.soul.moviedbkmp.android.presentation.navigation.BottomNavigationBar
import dev.soul.moviedbkmp.android.presentation.navigation.Screens
import dev.soul.moviedbkmp.android.presentation.screen.detail.ui.MovieDetailScreen
import dev.soul.moviedbkmp.android.presentation.screen.home.ui.HomeScreen
import dev.soul.moviedbkmp.android.presentation.screen.profile.ProfileScreen
import dev.soul.moviedbkmp.android.presentation.screen.settings.SettingsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        Screens.Home,
        Screens.Profile,
        Screens.Settings
    )

    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF8A1C1C).copy(alpha = 0.6f), // First color with 60% opacity
            Color(0xFF200B0B).copy(alpha = 0.6f)  // Second color with 60% opacity
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { /* Leave this empty to make the navigation floating */ }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route,
                modifier = Modifier
                    .padding(paddingValues)
                    .background(
                        brush = gradient,
                    )
            ) {
                composable(Screens.Home.route) { HomeScreen(navController) }
                composable(Screens.Profile.route) { ProfileScreen(navController) }
                composable(Screens.Settings.route) { SettingsScreen(navController) }
                composable(
                    Screens.Details.route,
                    arguments = listOf(navArgument("itemId") { type = NavType.LongType })
                ) {
                    it.arguments?.getLong("itemId")?.let { movieId ->
                        MovieDetailScreen(navController, movieId)
                    }
                }
            }

            val currentDestination =
                navController.currentBackStackEntryFlow.collectAsState(initial = navController.currentBackStackEntry).value?.destination?.route

            if (currentDestination in listOf(
                    Screens.Home.route,
                    Screens.Profile.route,
                    Screens.Settings.route
                )
            ) {
                BottomNavigationBar(
                    navController = navController,
                    items = bottomNavItems,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    }
}