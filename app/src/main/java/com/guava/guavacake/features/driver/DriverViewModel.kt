package com.guava.guavacake.features.driver

import android.util.Log
import androidx.lifecycle.*
import com.github.shiguruikai.combinatoricskt.permutations
import com.guava.guavacake.features.driver.DriverViewModel.Companion.VOWELS
import com.guava.guavacake.features.driver.model.DriverCache
import com.guava.guavacake.features.driver.model.DriverModel
import com.guava.guavacake.features.driver.model.Route
import com.guava.guavacake.network.GuavaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(val repository: GuavaRepository): ViewModel() {
    val totalSS = MutableLiveData<Double>()
    val totalSSString: LiveData<String> = Transformations.map(totalSS) {
        "Optimal SS: $it"
    }
    val optimalRoute = MutableLiveData<List<Route>>()
    val error = MutableLiveData<String>()

    init {
        getDrivers()
    }

    fun getDrivers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDrivers().catch {
                error.postValue(it.message)
            }.collect {
                it.allRouteCombinations().maxByOrNull { it.getSS() }?.let {
                    optimalRoute.postValue(it)
                    totalSS.postValue(it.getSS())
                } ?: run {
                    error.postValue("No maximal route found")
                }
            }
        }
    }

    companion object {
        const val TAG = "DriverViewModel"
        const val EVEN_SHIPMENT_MULTIPLIER = 1.5
        const val ODD_SHIPMENT_MULTIPLIER = 1.0
        const val BONUS_MULTIPLIER = 1.5
        val VOWELS = hashSetOf('a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y')
    }
}

fun Char.isVowel(): Boolean {
    return this in VOWELS
}

fun Int.getFactorsExclude1(): HashSet<Int> {
    val start = 2
    val maxFactor = this/2
    return (start..maxFactor).filter { it.isFactorOf(this) }.toHashSet()
}

fun Int.isFactorOf(other: Int): Boolean {
    return other % this == 0
}

fun DriverModel.allRouteCombinations(): List<List<Route>> {
    val driverPermutations = drivers.permutations(shipments.size)
    val uniqueRoutes = mutableListOf<List<Route>>()
    for (driverPermutation in driverPermutations) {
        val routeList = driverPermutation.zip(shipments) { driver, shipment ->
            Route(driver, shipment)
        }
        uniqueRoutes.add(routeList)
    }
    return uniqueRoutes
}

fun Route.getSS(): Double {
    val baseSS: Double = if (streetName.length % 2 == 0) {
        driverName.filter { it.isVowel() }.length * DriverViewModel.EVEN_SHIPMENT_MULTIPLIER
    } else {
        driverName.filterNot{ it.isVowel() }.length * DriverViewModel.ODD_SHIPMENT_MULTIPLIER
    }
    val driverFactors = DriverCache.getFactorsWithCache(driverName.length)
    val shipmentFactors = DriverCache.getFactorsWithCache(streetName.length)
    for (factor in driverFactors) {
        if (factor in shipmentFactors) {
            return baseSS * DriverViewModel.BONUS_MULTIPLIER
        }
    }
    return baseSS
}

fun List<Route>.getSS(): Double {
    return this.sumOf { DriverCache.getSSWithCache(it) }
}