package org.example.xygos.newmaths

import platform.UIKit.UIColor

actual class MathRendererFactory {
    actual fun createRenderer(): MathRenderer1 {
        TODO("Not yet implemented")
    }
}

class IosMathRenderer(private val label: MTMathUILabel): MathRenderer1 {
    override fun render(formula: String) {
        label.latex = formula
        val operators = listOf('+', '-', '*', '/', '%', '=', '^', '<', '>', '!', '\'', '≤', '≥')
        if (operators.any { formula.contains(it) }) {
            label.textColor = UIColor.blueColor
        }
    }
}
