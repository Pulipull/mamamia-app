package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val mamamiaScheme = lightColorScheme(
            primary = Color(0xFF008C45), // Italian Green
            onPrimary = Color.White,
            primaryContainer = Color(0xFFE8F5E9), 
            onPrimaryContainer = Color(0xFF1B5E20),
            secondary = Color(0xFFCD212A), // Italian Red
            onSecondary = Color.White,
            background = Color(0xFFFDFCFB), // Warm Paper/Cream
            surface = Color.White,
            onSurface = Color(0xFF2C1B18) // Dark Coffee Brown
        )

        setContent {
            MaterialTheme(colorScheme = mamamiaScheme) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    bottomBar = {
                        // Hanya tampilkan BottomBar di layar utama (Home, Menu, Profile)
                        if (currentDestination?.route in listOf("home", "menu", "profile")) {
                            NavigationBar(
                                containerColor = Color.White,
                                tonalElevation = 8.dp
                            ) {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                    label = { Text("Home") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                                    onClick = {
                                        navController.navigate("home") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Menu, contentDescription = null) },
                                    label = { Text("Menu") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "menu" } == true,
                                    onClick = {
                                        navController.navigate("menu") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                                    label = { Text("Profile") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "profile" } == true,
                                    onClick = {
                                        navController.navigate("profile") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.background)
                            .italianTexture(),
                        color = Color.Transparent
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            enterTransition = { fadeIn(animationSpec = tween(400)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(400)) },
                            exitTransition = { fadeOut(animationSpec = tween(400)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(400)) },
                            popEnterTransition = { fadeIn(animationSpec = tween(400)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(400)) },
                            popExitTransition = { fadeOut(animationSpec = tween(400)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(400)) }
                        ) {
                            composable("home") { HomeScreen(navController) }
                            composable("menu") { MenuScreen(navController) }
                            composable(
                                "detail/{menuId}",
                                arguments = listOf(navArgument("menuId") { type = androidx.navigation.NavType.StringType })
                            ) { backStackEntry ->
                                val menuId = backStackEntry.arguments?.getString("menuId") ?: ""
                                DetailMenuScreen(navController, menuId)
                            }
                            composable("profile") { ProfileScreen(navController) }
                            composable("edit_profile") { EditProfileScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Menambahkan tekstur halus ala "Gingham" (taplak meja Italia) yang sangat transparan.
 * Memberikan karakter tanpa mengganggu keterbacaan atau terlihat amatir.
 */
fun Modifier.italianTexture(): Modifier = this.drawBehind {
    val step = 32.dp.toPx() // Kotak sedikit lebih rapat
    val alpha = 0.1f // Level profesional: Terlihat tapi tidak mengganggu teks
    val strokeWidth = 1.5.dp.toPx() // Kembali ke ketebalan standar

    // Garis Vertikal (Hijau Italia)
    var x = 0f
    while (x < size.width) {
        drawLine(
            color = Color(0xFF008C45).copy(alpha = alpha),
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = strokeWidth
        )
        x += step
    }

    // Garis Horizontal (Merah Italia)
    var y = 0f
    while (y < size.height) {
        drawLine(
            color = Color(0xFFCD212A).copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidth
        )
        y += step
    }
}
