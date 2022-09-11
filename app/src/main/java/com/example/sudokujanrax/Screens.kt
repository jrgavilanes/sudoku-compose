package com.example.sudokujanrax

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navigationController: NavHostController? = null) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var currentPage by remember { mutableStateOf("Home") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopAppBar(title = currentPage, onClickIcon = {
                coroutineScope.launch {
                    if (it == "menu") {
                        if (scaffoldState.drawerState.isClosed) {
                            scaffoldState.drawerState.open()
                        }
                    } else {
                        currentPage = it
                        scaffoldState.snackbarHostState.showSnackbar("cucu $it")
                    }
                }
            })
        },
        bottomBar = {
            MyBottomNavigation(
                onClickIcon = {
                    currentPage = it.id
                },
                currentPage = currentPage
            )
        },
        floatingActionButton = { MyFAB { navigationController?.navigate(Routes.ScreenGame.id) } },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerContent = {
            MyDrawer(onClickIcon = {
                coroutineScope.launch {
                    currentPage = it
                    scaffoldState.drawerState.close()
                }
            })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
//            when(currentPage) {
//                "home" -> Text(text = "Pantalla $currentPage")
//                else -> Text(text = "Pantalla desconocida")
//            }
            Text(text = "Pantalla $currentPage")
        }
    }
}

@Composable
fun MyTopAppBar(title: String, onClickIcon: (String) -> Unit) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = { onClickIcon("menu") }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("search") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
            IconButton(onClick = { onClickIcon("dangerous") }) {
                Icon(imageVector = Icons.Filled.Dangerous, contentDescription = "dangerous")
            }
        }
    )
}

@Composable
fun MyBottomNavigation(onClickIcon: (Routes) -> Unit, currentPage: String) {
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        BottomNavigationItem(
            selected = currentPage == Routes.ScreenHome.id,
            onClick = { onClickIcon(Routes.ScreenHome) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text(Routes.ScreenHome.id) }
        )
        BottomNavigationItem(
            selected = currentPage == Routes.ScreenGame.id,
            onClick = { onClickIcon(Routes.ScreenGame) },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) },
            label = { Text(Routes.ScreenGame.id) }
        )
    }
}

@Composable
fun MyFAB(onClick: () -> Unit) {
    Column {
        FloatingActionButton(
            onClick = { onClick() },
            backgroundColor = Color.Yellow,
            contentColor = Color.Black
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}

@Composable
fun MyDrawer(onClickIcon: (String) -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(
            "Primera opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClickIcon("Primera opción") }
        )
        Text(
            "Segunda opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClickIcon("Segunda opción") }
        )
        Text(
            "Tercera opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClickIcon("Tercera opción") }
        )
    }
}
