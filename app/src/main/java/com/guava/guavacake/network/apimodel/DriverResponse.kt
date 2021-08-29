package com.guava.guavacake.network.apimodel

import com.guava.guavacake.features.driver.model.DriverModel

data class DriverResponse(
    val drivers: List<String>,
    val shipments: List<String>
) {
    fun toModel(): DriverModel {
        return DriverModel(drivers, shipments)
    }
}