package me.andresjaimes.exploracolombiaapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationApp(){
    val myNavController = rememberNavController()
    NavHost(
        navController = myNavController,
        startDestination = "register",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {}, onNavigateToRegister = {}
            )
        }
        composable(route = "register") {
            RegisterScreen(onRegisterSuccess = {}, onNavigateToLogin = {})
        }
    }

}


