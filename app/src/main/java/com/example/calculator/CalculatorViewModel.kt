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

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Delete -> performDeletion()
            CalculatorAction.Calculate -> performCalculation()
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when(state.operator) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Subtract -> number1 - number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operator = null
            )
        }

    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(number2 = state.number2.dropLast(1))
            state.operator != null -> state = state.copy(operator = null)
            state.number1.isNotBlank() -> state = state.copy(number1 = state.number1.dropLast(1))
        }
    }

    private fun enterDecimal() {
        if (state.number1.isNotBlank() && state.operator == null && !state.number1.contains('.')) {
            state = state.copy(number1 = state.number1 + ".")
        }
        else if (state.number2.isNotBlank() && state.number2.contains('.')) {
            state = state.copy(number2 = state.number2 + ".")
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operator = operation)
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operator == null && state.number1.length < MAX_NUM_LENGTH) {
            state = state.copy(number1 = state.number1 + number)
        }
        else if(state.operator!= null && state.number2.length < MAX_NUM_LENGTH) {
            state = state.copy(number2 = state.number2 + number)
        }
    }

    // It is like java static member
    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}