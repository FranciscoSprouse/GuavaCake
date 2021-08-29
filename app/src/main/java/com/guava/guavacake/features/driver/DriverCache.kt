package com.guava.guavacake.features.driver.model

import com.guava.guavacake.features.driver.getFactorsExclude1
import com.guava.guavacake.features.driver.getSS

object DriverCache {
    val factorCache = hashMapOf<Int, HashSet<Int>>()

    fun getFactorsWithCache(factor: Int): HashSet<Int> {
        factorCache[factor]?.let {
            return it
        } ?: run {
            val factors = factor.getFactorsExclude1()
            factorCache[factor] = factors
            return factors
        }
    }

    val routeCache = hashMapOf<Route, Double>()

    fun getSSWithCache(route: Route): Double {
        routeCache[route]?.let {
            return it
        } ?: run {
            val ss = route.getSS()
            routeCache[route] = ss
            return ss
        }
    }
}