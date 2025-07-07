package com.saveetha.ratemyprof

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel : ViewModel() {

    private val _professorCount = MutableStateFlow(0)
    val professorCount: StateFlow<Int> = _professorCount

    private val _studentCount = MutableStateFlow(0)
    val studentCount: StateFlow<Int> = _studentCount

    private val _reviewCount = MutableStateFlow(0)
    val reviewCount: StateFlow<Int> = _reviewCount

    init {
        fetchCounts()
    }

    private fun fetchCounts() {
        viewModelScope.launch {
            delay(500) // simulate delay
            _professorCount.value = 87
            _studentCount.value = 50
            _reviewCount.value = 32
        }
    }
}
