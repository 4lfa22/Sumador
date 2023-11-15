package com.example.sumador

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sumador.ui.theme.SumadorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SumadorScreen(navController: NavHostController) {
    val viewModel: SumadorViewModel = viewModel()

    var mostrarResultado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }

        TextField(
            value = text1,
            onValueChange = {
                text1 = it
                viewModel.numero1 = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
        )

        TextField(
            value = text2,
            onValueChange = {
                text2 = it
                viewModel.numero2 = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.resultado = viewModel.numero1 + viewModel.numero2
                    viewModel.sumasRealizadas.add("${viewModel.numero1} + ${viewModel.numero2} = ${viewModel.resultado}")
                    mostrarResultado = true

                    navController.navigate("pantallaResultados")
                }
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (mostrarResultado) {
            LazyColumn {
                items(viewModel.sumasRealizadas.size) { index ->
                    Text("Realizado: ${viewModel.sumasRealizadas[index]}")
                }
            }

            Button(
                onClick = {
                    mostrarResultado = false
                    text1 = "0"
                    text2 = "0"
                    navController.popBackStack("pantallaPrincipal", inclusive = false)
                },
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                Text("Volver")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SumadorScreenPreview() {
    SumadorTheme {
        SumadorScreen(navController = rememberNavController())
    }
}
