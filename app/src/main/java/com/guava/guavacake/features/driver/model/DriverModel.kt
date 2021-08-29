package com.guava.guavacake.features.driver.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly
import java.lang.Integer.min

data class DriverModel(
    val drivers: List<String>,
    val shipments: List<String>
) {
    // Assumes Shipments will be in the format
    // "Number StreetName OptionalNumber"
    val shipmentStreetNames: List<String>
        get() {
            return shipments.map { shipment ->
                shipment.filter {
                    !it.isDigit()
                }.trim()
            }
    }

    val allRoutes: List<List<Route>>
        get() {
            val routeLists = mutableListOf<MutableList<Route>>()
            drivers.forEachIndexed { driverIndex, driver ->
                shipments.forEachIndexed { streetIndex, street ->
                    // Assumes drivers.size == shipments.size
                    val index = (streetIndex + driverIndex) % drivers.size
                    if (routeLists.size <= index) {
                        routeLists.add(mutableListOf())
                    }
                    routeLists[index].add(Route(driver, street))
                }

            }
            return routeLists
        }
}

data class Route(val driverName: String, val streetName: String)