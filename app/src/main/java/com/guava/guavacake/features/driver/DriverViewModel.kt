package com.guava.guavacake.features.driver

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guava.guavacake.network.GuavaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(val repository: GuavaRepository): ViewModel() {

    init {
        getDrivers()
    }

    fun getDrivers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDrivers().catch {

            }.collect {
                Log.d("test", it.toString())
            }
        }
    }
}