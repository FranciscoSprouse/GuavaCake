package com.guava.guavacake.features.driver.model

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
}

data class Route(val driverName: String, val streetName: String)