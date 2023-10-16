package com.alf.tecnica.repository

import com.alf.tecnica.services.RetroFitClient
import com.alf.tecnica.services.WebServices

class MockiRepositori {
    private var apiService: WebServices? = null

    init {
        apiService = RetroFitClient.getClient?.create(WebServices::class.java)
    }

    suspend fun getMocki() = apiService?.getMocki()
}