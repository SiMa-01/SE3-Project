package com.example.se3_app.firebase.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.se3_app.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInView(
    navController: NavController,
    signInViewModel: SignInViewModel,
    listViewModel: ListViewModel
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Registrieren"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp).testTag("loginView"),
            onClick = { navController.navigate("SignUpView") },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Color.Blue
            )
        )
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username = remember { mutableStateOf(TextFieldValue("")) }
        var password = remember { mutableStateOf(TextFieldValue("")) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "E-Mail") },
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier.testTag("emailField")
        )


        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Passwort") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            modifier = Modifier.testTag("passwordField")
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (username.value.text.isEmpty() || password.value.text.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Passwort und E-Mail darf nicht leer sein!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        signInViewModel.signInWithEmailAndPassword(
                            username.value.text,
                            password.value.text
                        )
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        if (signInViewModel.anmeldungOk && signInViewModel.verifiziert) {
                            listViewModel.userId = signInViewModel.userId!!
                            navController.navigate("splashScreen")
                        } else if (signInViewModel.anmeldungOk && !signInViewModel.verifiziert) {
                            Toast.makeText(
                                context,
                                "E-Mail-Adresse nicht verifiziert",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (!signInViewModel.anmeldungOk) {
                            Toast.makeText(context, "Anmeldung fehlgeschlagen", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("loginButton")
            ) {
                Text(text = "Login")
            }
            Text(text = signInViewModel.errorMessage)
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Passwort vergessen?"),
            onClick = { navController.navigate("ForgotPasswordView") },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}
