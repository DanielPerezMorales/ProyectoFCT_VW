package com.example.proyectofct.data.ktor.network

import com.example.proyectofct.data.ktor.model.factura_item_model
import com.example.proyectofct.data.ktor.model.factura_model

interface KtorService {
    suspend fun getAllFacturas(): factura_model?
}