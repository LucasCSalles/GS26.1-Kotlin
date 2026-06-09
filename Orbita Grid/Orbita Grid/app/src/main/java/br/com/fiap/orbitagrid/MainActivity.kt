package br.com.fiap.orbitagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.com.fiap.orbitagrid.ui.navigation.NavGraph
import br.com.fiap.orbitagrid.ui.theme.OrbitaGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrbitaGridTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
