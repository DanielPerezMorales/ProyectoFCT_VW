package com.example.proyectofct.ui.view.Activity

import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofct.R
import com.example.proyectofct.core.ViewPagerAdapter
import com.example.proyectofct.databinding.ActivityPantallaPrincipalSmartSolarBinding
import com.example.proyectofct.ui.view.Fragment.Mi_instalacion_fragment
import com.example.proyectofct.ui.view.Fragment.Detalles_fragment
import com.example.proyectofct.ui.view.Fragment.Energia_fragment
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class PantallaPrincipalSmartSolar : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaPrincipalSmartSolarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityPantallaPrincipalSmartSolarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.smartsolar_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{
            if(it.isSuccessful){
                 val cambioColor= Firebase.remoteConfig.getBoolean("CambioDeValores")
                if(cambioColor){
                    val colorConsumo = ContextCompat.getColor(this, R.color.color_consumo_2_0)
                    binding.ibBack.setColorFilter(colorConsumo)
                    binding.TVBack.setTextColor(colorConsumo)
                    binding.TVSmart.setTypeface(null, Typeface.ITALIC)
                }
            }
        }

        createTab()

        binding.ibBack.setOnClickListener { onBackPressed() }
    }

    private fun createTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Mi_instalacion_fragment(), "Mi instalación")
        adapter.addFragment(Energia_fragment(),"Energía")
        adapter.addFragment(Detalles_fragment(), "Detalles")

        binding.VP.adapter = adapter
        binding.tablayout.setupWithViewPager(binding.VP)
    }
}