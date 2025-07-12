package org.example.xygos.newmaths

import androidx.compose.runtime.Composable

expect class MathRenderer(textColor: String = "#000000", bgColor: String = "#FFFFFF") {
    @Composable
    fun RenderEquation(equation: String)
    fun updateEquation(equation: String)
}