package com.example.proyectofct.data.model


import com.example.proyectofct.data.database.entities.FacturaEntity
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class modelo_Factura(
    @SerializedName("numFacturas") val numFacturas: String,
    @SerializedName("facturas") val facturas: List<facturaItem>
)

data class facturaItem(
    @SerializedName("descEstado") val descEstado: String,
    @SerializedName("importeOrdenacion") val importeOrdenacion: Float,
    @SerializedName("fecha") val fecha: String
)

fun facturaItem.toFacturaEntity(): FacturaEntity {
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val dateFecha=formatoFecha.parse(fecha)
    val entity = FacturaEntity(fecha = dateFecha!!, estado = descEstado, precio = importeOrdenacion)
    return entity
}

