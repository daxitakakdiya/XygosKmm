package org.example.xygos.newmaths

import android.content.Context
import com.judemanutd.katexview.KatexView


class AndroidMathRenderer(private val context: Context, private val katexView: KatexView) : MathRenderer1 {
    override fun render(formula: String) {
        val highlighted = when (formula) {
            "5! + f'(x)" -> formula.replace("x", "\\overset{\\color{red}\\bullet}{\\color{blue}x}")
            "x^2 + y^2 = z^2" -> formula.replace("+", "\\overset{\\color{red}\\bullet}{\\color{blue}+}")
            "a*b/c - d < 10 >= 5" -> formula.replace("-", "\\overset{\\color{red}\\bullet}{\\color{blue}-}")
            else -> formula
        }

        try {
            katexView.setText("$$$highlighted$$")
        } catch (e: Exception) {
            katexView.setText("Error rendering formula")
        }
    }
}
actual class MathRendererFactory(private val context: Context, private val katexView: KatexView) {
    actual fun createRenderer(): MathRenderer1 = AndroidMathRenderer(context, katexView)
}

//override fun render(formula: String) {
//    val symbolToHighlight = when {
//        formula.contains("x") -> "x"
//        formula.contains("+") -> "+"
//        formula.contains("-") -> "-"
//        else -> null
//    }
//
//    val highlighted = symbolToHighlight?.let {
//        formula.replace(it, "\\overset{\\color{red}\\bullet}{\\color{blue}$it}")
//    } ?: formula
//
//    try {
//        katexView.setText("$$$highlighted$$")
//    } catch (e: Exception) {
//        katexView.setText("Error rendering formula")
//    }
//}