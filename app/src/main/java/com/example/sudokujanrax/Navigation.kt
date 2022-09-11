package com.example.sudokujanrax // ktlint-disable filename

sealed class Routes(val id: String) {
    object ScreenHome : Routes("home")
    object ScreenGame : Routes("game")
//    object Pantalla4 : Routes("pantalla4/{name}") {
//        fun createRoute(name: String) = "pantalla4/$name"
//    }
}
