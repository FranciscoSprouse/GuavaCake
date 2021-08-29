package com.guava.guavacake.network

import com.guava.guavacake.network.apimodel.DriverResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GuavaRepository @Inject constructor(val networkDataSource: GuavaApiService) {

    suspend fun getDrivers(): Flow<DriverResponse> = flow {
        emit(networkDataSource.getDrivers())
    }
}