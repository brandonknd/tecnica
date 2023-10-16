package com.alf.tecnica.services

import com.alf.tecnica.model.MockiDataModel
import retrofit2.Response
import retrofit2.http.GET

interface WebServices {
    @GET("v1/eeced007-6b29-4c9d-ab63-c115a990d927")
    suspend fun getMocki(): Response<java.util.ArrayList<MockiDataModel>>
}