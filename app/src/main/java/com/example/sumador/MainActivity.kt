package com.example.sumador


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "pantallaPrincipal") {
                composable("pantallaPrincipal") {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        SumadorScreen(navController = navController)
                    }
                }
                composable("pantallaResultados") {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        PantallaResultados(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaResultados(navController: NavHostController) {
    val viewModel: SumadorViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(viewModel.sumasRealizadas.size) { index ->
                Text("Realizado: ${viewModel.sumasRealizadas[index]}")
            }
        }

        if (viewModel.mostrarResultado) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Último resultado: ${viewModel.numero1} + ${viewModel.numero2} = ${viewModel.resultado}")
        }

        Button(
            onClick = {
                navController.navigate("pantallaPrincipal") {
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text("Volver")
        }
    }
}
