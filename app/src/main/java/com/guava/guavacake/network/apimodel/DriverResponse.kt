package com.guava.guavacake.network.apimodel

data class DriverResponse(
    val drivers: List<String>,
    val shipments: List<String>
)