package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * View Model is used for state management
 */
class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set // private setter
}