package com.example.proyectofct.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofct.core.AppNavigation
import com.example.proyectofct.ui.view.activity.ui.theme.ProyectoFCTTheme

class MainCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ProyectoFCTTheme {
                androidx.compose.material.Surface(color = androidx.compose.material.MaterialTheme.colors.background) {
                    AppNavigation(this)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoFCTTheme {
        //AppNavigation()
    }
}