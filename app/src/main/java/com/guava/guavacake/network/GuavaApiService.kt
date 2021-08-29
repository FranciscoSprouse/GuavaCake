package com.guava.guavacake.network

import com.guava.guavacake.network.apimodel.DriverResponse
import retrofit2.http.GET

interface GuavaApiService {
    @GET("v1/drivers")
    suspend fun getDrivers(): DriverResponse
}