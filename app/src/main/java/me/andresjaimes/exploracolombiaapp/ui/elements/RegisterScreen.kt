package me.andresjaimes.exploracolombiaapp.ui.elements

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import me.andresjaimes.exploracolombiaapp.ui.theme.ExploraColombiaAppTheme

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    // Estados para manejar la autenticación
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val primaryOrange = Color(0xFFE45D25)
    val lightGrayBg = Color(0xFFF8F9FE)
    val inputBg = Color(0xFFE5E5EA)

    val auth = Firebase.auth

    // Función de validación de campos
    fun validateInputs(): Boolean {
        errorMessage = null

        if (name.isBlank()) {
            errorMessage = "Por favor ingresa tu nombre completo"
            return false
        }

        if (email.isBlank()) {
            errorMessage = "Por favor ingresa tu correo electrónico"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Por favor ingresa un correo electrónico válido"
            return false
        }

        if (password.isBlank()) {
            errorMessage = "Por favor ingresa una contraseña"
            return false
        }

        if (password.length < 6) {
            errorMessage = "La contraseña debe tener al menos 6 caracteres"
            return false
        }

        if (password != confirmPassword) {
            errorMessage = "Las contraseñas no coinciden"
            return false
        }

        if (!acceptedTerms) {
            errorMessage = "Debes aceptar los términos y condiciones"
            return false
        }

        return true
    }

    // Función para registrar usuario en Firebase
    fun performRegistration() {
        if (!validateInputs()) return

        isLoading = true
        errorMessage = null

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                    )?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                        }
                    }
                    onRegisterSuccess()
                } else {
                    errorMessage = when (task.exception?.message) {
                        "The email address is already in use by another account." ->
                            "Este correo electrónico ya está registrado"
                        "The email address is badly formatted." ->
                            "El formato del correo electrónico no es válido"
                        "Password should be at least 6 characters" ->
                            "La contraseña debe tener al menos 6 caracteres"
                        else -> task.exception?.message ?: "Error al registrar usuario"
                    }
                }
            }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = lightGrayBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNavigateToLogin() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = primaryOrange
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Explorando Colombia",
                color = primaryOrange,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Crea tu cuenta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Empieza tu aventura por el realismo mágico",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (errorMessage != null) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE5E5)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                RegisterField(
                    label = "NOMBRE COMPLETO",
                    value = name,
                    onValueChange = {
                        name = it
                        errorMessage = null
                    },
                    placeholder = "Tu nombre",
                    leadingIcon = Icons.Default.Person,
                    inputBg = inputBg,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(20.dp))

                RegisterField(
                    label = "CORREO ELECTRÓNICO",
                    value = email,
                    onValueChange = {
                        email = it
                        errorMessage = null
                    },
                    placeholder = "hola@ejemplo.com",
                    leadingIcon = Icons.Default.Email,
                    inputBg = inputBg,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(20.dp))

                RegisterField(
                    label = "CONTRASEÑA",
                    value = password,
                    onValueChange = {
                        password = it
                        errorMessage = null
                    },
                    placeholder = "........",
                    leadingIcon = Icons.Default.Lock,
                    inputBg = inputBg,
                    isPassword = true,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(20.dp))

                RegisterField(
                    label = "CONFIRMAR CONTRASEÑA",
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        errorMessage = null
                    },
                    placeholder = "........",
                    leadingIcon = Icons.Default.Refresh,
                    inputBg = inputBg,
                    isPassword = true,
                    enabled = !isLoading
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = acceptedTerms,
                    onCheckedChange = {
                        if (!isLoading) acceptedTerms = it
                    },
                    colors = CheckboxDefaults.colors(checkedColor = primaryOrange),
                    enabled = !isLoading
                )
                Text(
                    text = buildAnnotatedString {
                        append("Acepto los ")
                        withStyle(style = SpanStyle(color = primaryOrange, fontWeight = FontWeight.Bold)) {
                            append("términos y condiciones")
                        }
                        append(" así como el tratamiento de datos personales.")
                    },
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { performRegistration() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                enabled = !isLoading
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(primaryOrange, Color(0xFFFF8A65))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Registrarse", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), thickness = 0.5.dp, color = Color.LightGray)
                Text(
                    text = " O REGÍSTRATE CON ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                HorizontalDivider(modifier = Modifier.weight(1f), thickness = 0.5.dp, color = Color.LightGray)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SocialButton(
                    text = "Google",
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Email,
                    enabled = !isLoading
                )
                SocialButton(
                    text = "Apple",
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Lock,
                    enabled = !isLoading
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(text = "¿Ya tienes una cuenta? ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "Inicia sesión",
                    color = primaryOrange,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        if (!isLoading) onNavigateToLogin()
                    }
                )
            }
        }
    }
}

@Composable
fun RegisterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    inputBg: Color,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    enabled: Boolean = true
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp)),
            placeholder = { Text(placeholder, color = Color.Gray) },
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = Color.Gray) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = inputBg,
                unfocusedContainerColor = inputBg
            ),
            singleLine = true,
            enabled = enabled
        )
    }
}

@Composable
fun SocialButton(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = { /* TODO: Implement Google/Apple Sign-In */ },
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        enabled = enabled
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    ExploraColombiaAppTheme() {
        RegisterScreen(onRegisterSuccess = {}, onNavigateToLogin = {})
    }
}