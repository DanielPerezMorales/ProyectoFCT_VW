package com.example.proyectofct.ui.view.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofct.R
import com.example.proyectofct.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class Pagina_Principal : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ibPractica1.setOnClickListener{
            val intent=Intent(this, Facturas::class.java)
            startActivity(intent)
        }

        binding.ibPractica2.setOnClickListener{
            val intent=Intent(this, PantallaPrincipalSmartSolar::class.java)
            startActivity(intent)
        }

        binding.ibNavegacion.setOnClickListener{
            val intent=Intent(this, Activity_Navegacion::class.java)
            startActivity(intent)
        }

        val configSettings:FirebaseRemoteConfigSettings= remoteConfigSettings {
            minimumFetchIntervalInSeconds=0
        }
        val firebaseConfig= Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
        firebaseConfig.setDefaultsAsync(mapOf(("Visualizacion_ListadoFacturas") to true, ("CambioDeValores") to false))

    }
}