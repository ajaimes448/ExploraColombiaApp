package me.andresjaimes.exploracolombiaapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun HomeScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAligment= Alignment.Center){
        Text("Home Screen", fontSize = 30.sp)
    }
}