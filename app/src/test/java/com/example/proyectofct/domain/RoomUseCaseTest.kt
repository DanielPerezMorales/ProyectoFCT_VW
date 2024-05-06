package com.example.proyectofct.domain

import com.example.proyectofct.data.database.FacturaDatabase
import com.example.proyectofct.data.database.dao.Factura_dao
import com.example.proyectofct.data.database.entities.FacturaEntity
import com.example.proyectofct.data.model.facturaItem
import com.example.proyectofct.data.model.toFacturaEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RoomUseCaseTest {
    @Mock
    private lateinit var facturaDatabase: FacturaDatabase

    lateinit var roomUseCase: RoomUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        facturaDatabase = mockk()
        roomUseCase = RoomUseCase()
    }

    @Test
    fun insertFacturasToRoom() = runBlocking {
        // Given
        val facturaDao = mockk<Factura_dao>()
        var lista: List<facturaItem> = listOf(facturaItem("Pagada", 100.0F, "07/12/2019"), facturaItem("Pendiente de pago",50.99F, "21/03/2020"))
        val listaEntity=lista.map { it.toFacturaEntity() }
        coEvery { facturaDatabase.getFactureDao() } returns facturaDao

        // When
        roomUseCase.insertFacturasToRoom(listaEntity, facturaDatabase)

        // Then
        coVerify(exactly = 1) { facturaDao.insertAll(listaEntity) }
    }

    @Test
    fun deleteAllFacturasFromRoom() = runBlocking {
        // Given
        val facturaDao = mockk<Factura_dao>()
        coEvery { facturaDatabase.getFactureDao() } returns facturaDao

        // When
        roomUseCase.deleteAllFacturasFromRoom(facturaDatabase)

        // Then
        coVerify(exactly = 1) { facturaDao.deleteAllFacturas() }
    }
}