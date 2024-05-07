package com.example.proyectofct.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockRandom
import co.infinum.retromock.meta.MockResponse
import com.example.proyectofct.data.model.Modelo_Detalles
import com.example.proyectofct.data.model.modelo_Factura
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("facturas")
    suspend fun getAllFacturas(): Response<modelo_Factura>

    @Mock
    @MockRandom
    @MockResponse(body = "facturas.json")
    @MockResponse(body = "facturas2.json")
    @MockResponse(body = "facturas3.json")
    @GET("facturas")
    fun getFacturasMock(): Call<modelo_Factura?>

    @Mock
    @MockRandom
    @MockResponse(body = "detalles_fragment.json")
    @MockResponse(body = "detalles_fragment2.json")
    @MockResponse(body = "detalles_fragment3.json")
    @MockResponse(body = "detalles_fragment4.json")
    @MockResponse(body = "detalles_fragment5.json")
    @GET("/")
    fun getDetallesMock(): Call<Modelo_Detalles?>
}