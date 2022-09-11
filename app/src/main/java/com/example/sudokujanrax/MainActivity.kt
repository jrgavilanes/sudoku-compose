package com.example.sudokujanrax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sudokujanrax.ui.theme.SudokuJanraxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SudokuJanraxTheme {
                val navigationController = rememberNavController()
                NavHost(
                    navController = navigationController,
                    startDestination = Routes.ScreenHome.id
                ) {
                    composable(route = Routes.ScreenHome.id) { MainScreen(navigationController) }
                    composable(route = Routes.ScreenGame.id) { GameScreen(navigationController) }
//                    composable(
//                        route = Routes.Pantalla4.route,
//                        arguments = listOf(
//                            navArgument("name") {
//                                type = NavType.StringType
//                                defaultValue = ""
//                            }
//                        )
//                    ) {
//                        val nombre = it.arguments?.getString("name").orEmpty()
//                        Screen4(navigationController, nombre)
//                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SudokuJanraxTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreen(navigationController: NavHostController? = null) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Text(
                text = "HOla esta guay",
                Modifier.clickable { navigationController?.navigate(Routes.ScreenHome.id) }
                    .fillMaxWidth().background(
                        Color.Red
                    )
            )
            Row(Modifier.weight(1f)) {
                Recycler()
            }
            Row(Modifier.fillMaxWidth().background(Color.Blue)) {
                Text(text = "Opcion 1")
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Opcion 2")
            }
        }
    }
}

@Composable
fun Recycler() {
    LazyColumn() {
        (1..2000).map {
            item { Text("numero $it", Modifier.padding(2.dp)) }
        }
    }
}
