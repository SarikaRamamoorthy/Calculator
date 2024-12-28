package com.example.calculator

sealed class CalculatorAction {
    data class Number(val number: Int): CalculatorAction()
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    object Delete: CalculatorAction()
    object Clear: CalculatorAction()
}