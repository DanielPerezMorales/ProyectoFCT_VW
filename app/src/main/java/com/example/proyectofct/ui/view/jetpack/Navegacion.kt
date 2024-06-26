package com.example.proyectofct.ui.view.jetpack

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.proyectofct.R

private const val LINK_IBERDROLA = "https://www.iberdrola.es"

@Composable
fun Navegacion(context: Context?, navController:NavController?) {
    var url by remember { mutableStateOf("") }
    val openBrowser =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != RESULT_OK) {
                Toast.makeText(
                    context,
                    "No se pudo abrir el navegador",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.transparente),
            elevation = 0.dp
        ) {
            IconButton(onClick = { navController?.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(id = R.string.SS_atras), fontSize = 20.sp,
                color = colorResource(
                    id = R.color.color_consumo
                )
            )
        }
        Text(
            text = stringResource(id = com.example.proyectofct.R.string.Nav_TV_Navegacion),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(LINK_IBERDROLA))
                        openBrowser.launch(intent)
                        url = ""
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No se pudo abrir el navegador",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = com.example.proyectofct.R.color.color_consumo))
            ) {
                Text(
                    text = stringResource(id = com.example.proyectofct.R.string.Nav_abrir_navegador_externo),
                    color = Color.White
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(5.dp))
            Button(
                onClick = {
                    url = LINK_IBERDROLA
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = com.example.proyectofct.R.color.color_consumo))
            ) {
                Text(
                    text = stringResource(id = com.example.proyectofct.R.string.Nav_abrir_webview),
                    color = Color.White
                )
            }
        }
        LikeWebView(url)
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LikeWebView(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNavegacion() {
    Navegacion(null, null)
}