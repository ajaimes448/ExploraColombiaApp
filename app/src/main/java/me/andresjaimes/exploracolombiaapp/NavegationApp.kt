package me.andresjaimes.exploracolombiaapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun NavigationApp() {
    val myNavController = rememberNavController()
    val auth = Firebase.auth
    val startDestination = remember {
        if (auth.currentUser != null) "home" else "login"
    }

    NavHost(
        navController = myNavController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    myNavController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    myNavController.navigate("register")
                }
            )
        }

        composable(route = "register") {
            RegisterScreen(
                onRegisterSuccess = {
                    myNavController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    myNavController.navigate("login")
                }
            )
        }

        composable(route = "home") {
            HomeScreen(
                onSignOut = {
                    auth.signOut()
                    myNavController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}


