package org.example.xygos.newmaths

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
actual fun MathViewold(formula: String, modifier: Modifier) {
}

actual class MathView actual constructor() {
    actual fun renderEquation(equation: String) {
    }

    actual fun setOnEquationClickListener(listener: (String) -> Unit) {
    }
}