package me.andresjaimes.exploracolombiaapp.ui.elements


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaceScreen(
    navController: NavController? = null
) {
    val scrollState = rememberScrollState()
    val orangeColor = Color(0xFFFF6B35)
    val lightOrange = Color(0xFFFFF0E8)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Lugar", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Volver */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = orangeColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card del título principal
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lightOrange
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Comparte tu descubrimiento",
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.headlineMedium,
                        color = orangeColor,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Ayuda a otros viajeros a encontrar los tesoros escondidos de nuestra tierra.",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Card del formulario
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Campo: Nombre del lugar
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("NOMBRE DEL LUGAR") },
                        placeholder = { Text("Ej: Cascada del Fin del Mundo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = orangeColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = orangeColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Departamento
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("DEPARTAMENTO") },
                        placeholder = { Text("Ej: Putumayo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = orangeColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = orangeColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Ciudad
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("CIUDAD") },
                        placeholder = { Text("Ej: Mocoa") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = orangeColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = orangeColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Descripción
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("DESCRIPCIÓN") },
                        placeholder = { Text("Cuéntanos por qué este lugar es especial...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        minLines = 5,
                        maxLines = 10,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = orangeColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = orangeColor
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botón Publicar
                    Button(
                        onClick = { /* Publicar */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = orangeColor
                        )
                    ) {
                        Text("Publicar", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    }
}