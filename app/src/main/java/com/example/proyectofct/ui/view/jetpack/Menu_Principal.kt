package com.example.proyectofct.ui.view.jetpack

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Switch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofct.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


@Composable
fun Menu_principal(navController: NavController?, email: String?, pass: String?, check: Boolean?, date: String?) {
    Body_menu(navController, date)
    if (check == true) {
        val prefs = LocalContext.current.getSharedPreferences(
            LocalContext.current.getString(R.string.sheredPrefJetpack),
            Context.MODE_PRIVATE
        ).edit()
        prefs.clear()
        prefs.putString("email", email)
        prefs.putString("password", pass)
        prefs.putBoolean("check", check)
        prefs.putString("date", date)
        prefs.apply()
    }
}

@Composable
fun Body_menu(navController: NavController?, date: String?) {
    var seeList by remember { mutableStateOf(true) }
    val configSettings: FirebaseRemoteConfigSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 0
    }
    val firebaseConfig = Firebase.remoteConfig
    firebaseConfig.setConfigSettingsAsync(configSettings)
    firebaseConfig.setDefaultsAsync(
        mapOf("Visualizacion_ListadoFacturas" to true, "CambioDeValores" to false)
    )
    Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
        if (it.isSuccessful) {
            val visualizarListado = Firebase.remoteConfig.getBoolean("Visualizacion_ListadoFacturas")
            seeList = visualizarListado
        }
    }

    Column(Modifier.background(Color.White)) {
        var isActive by remember { mutableStateOf(false) }
        var ktor by remember { mutableStateOf(false) }
        val context = LocalContext.current
        Titulo()
        Spacer(modifier = Modifier.height(20.dp))
        TextWithButton("Práctica 1") {
            navController?.navigate("practica1/$isActive/$seeList/$ktor")
        }
        TextWithButton("Práctica 2") {
            navController?.navigate("SS")
        }
        TextWithButton("Navegación") {
            navController?.navigate("navegador")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "Activar Mock",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Switch(checked = isActive, onCheckedChange = {
                isActive = it
                if (isActive) {
                    Toast.makeText(context, "Mock activado", Toast.LENGTH_SHORT).show()
                    ktor = false
                } else {
                    Toast.makeText(context, "Mock desactivado", Toast.LENGTH_SHORT).show()
                }
            }, Modifier.padding(16.dp))
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "Activar KTOR",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Switch(checked = ktor, onCheckedChange = {
                ktor = it
                if (ktor) {
                    Toast.makeText(context, "KTOR activado", Toast.LENGTH_SHORT).show()
                    isActive = false
                } else {
                    Toast.makeText(context, "KTOR desactivado", Toast.LENGTH_SHORT).show()
                }
            }, Modifier.padding(16.dp))
        }

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp
        )
        Spacer(modifier = Modifier.height(100.dp))
        TextWithButtonSignOut {
            val prefs =
                context.getSharedPreferences(
                    context.getString(R.string.sheredPrefJetpack),
                    Context.MODE_PRIVATE
                ).edit()
            prefs.clear()
            prefs.putString("date", date)
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            navController?.navigate("login")
        }
    }
}

@Composable
fun Titulo() {
    Text(text = "Prácticas Android", Modifier.padding(top = 60.dp, start = 15.dp), fontSize = 30.sp)
}

@Composable
fun TextWithButton(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        IconButton(
            onClick = onClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Forward Icon"
            )
        }
    }

    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp
    )
}

@Composable
private fun TextWithButtonSignOut(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.PaginaPrincipal_cerrar_sesi_n),
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        IconButton(
            onClick = onClick,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                contentDescription = "Sign Out Icon"
            )
        }
    }

    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp
    )
}

@Preview(showSystemUi = true)
@Composable
fun Preview_menu() {
    Menu_principal(navController = null, email = "null", pass = "pass", check = false, "1970-01-01")
}