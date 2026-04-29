package me.andresjaimes.exploracolombiaapp.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import me.andresjaimes.exploracolombiaapp.ui.theme.ExploraColombiaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavegateToAddPlace: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(title={
                Text(text = "Explora Colombia")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {onNavegateToAddPlace()}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(paddingValues = innerPadding)) { }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ExploraColombiaAppTheme {
        HomeScreen()
    }
}