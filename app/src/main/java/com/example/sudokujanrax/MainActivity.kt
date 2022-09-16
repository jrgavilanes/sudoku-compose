package com.example.sudokujanrax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Text(
                text = "HOla esta guayss",
                Modifier.clickable { navigationController?.navigate(Routes.ScreenHome.id) }
                    .fillMaxWidth().background(
                        Color.Red
                    )
            )
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Recycler() {
    var selectedPosition by remember { mutableStateOf(-1) }
    var lista = (1..81).map {
        val c = if ((it % 9) == 0) {
            '9'
        } else {
            (it % 9).toString()[0]
        }
        c
    }.toCharArray()
    var sudoku by remember { mutableStateOf(lista) }
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.SpaceAround) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(9),
            contentPadding = PaddingValues(4.dp),
            content = {
                sudoku.mapIndexed { index, value ->
                    val cellNumber = index + 1
                    item {
                        var bgColor = if (cellNumber in listOf(
                                1, 2, 3,
                                10, 11, 12,
                                19, 20, 21,
                                7, 8, 9,
                                16, 17, 18,
                                25, 26, 27,
                                31, 32, 33,
                                40, 41, 42,
                                49, 50, 51,
                                55, 56, 57,
                                64, 65, 66,
                                73, 74, 75,
                                61, 62, 63,
                                70, 71, 72,
                                79, 80, 81
                            )
                        ) Color.Red else Color.Yellow
                        if (cellNumber == selectedPosition) bgColor = Color.White
                        Box(
                            modifier = Modifier
                                .background(bgColor)
                                .border(1.dp, Color.Black)
                                .padding(4.dp)
                                .size(30.dp)
                                .clickable {
                                    selectedPosition = cellNumber
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(value.toString(), fontSize = 14.sp)
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.size(56.dp))

        Text("Selecciona el numerín", modifier = Modifier.padding(vertical = 4.dp))

        LazyVerticalGrid(cells = GridCells.Fixed(10), contentPadding = PaddingValues(4.dp)) {
            (1..9).map { value ->
                item {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .border(1.dp, Color.Black)
                            .padding(4.dp)
                            .size(30.dp)
                            .clickable {
                                if (selectedPosition - 1 >= 0) {
                                    lista[selectedPosition - 1] = value.toString()[0]
                                    sudoku = lista.clone()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(value.toString(), fontSize = 14.sp)
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                        .size(30.dp)
                        .clickable {
                            lista[selectedPosition - 1] = ' '
                            sudoku = lista.clone()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "DEL")
                }
            }
        }
    }
}
