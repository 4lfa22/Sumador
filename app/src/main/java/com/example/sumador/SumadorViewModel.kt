package com.example.sumador

import androidx.lifecycle.ViewModel

class SumadorViewModel : ViewModel() {
    var numero1: Double = 0.0
    var numero2: Double = 0.0
    var resultado: Double = 0.0
    var mostrarResultado: Boolean = false
    val sumasRealizadas = mutableListOf<String>()
}
