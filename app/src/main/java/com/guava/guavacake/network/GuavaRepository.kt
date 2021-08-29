package com.guava.guavacake.network

import com.guava.guavacake.features.driver.model.DriverModel
import com.guava.guavacake.network.apimodel.DriverResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GuavaRepository @Inject constructor(val networkDataSource: GuavaApiService) {

    suspend fun getDrivers(): Flow<DriverModel> = flow {
        emit(networkDataSource.getDrivers().toModel())
    }
}