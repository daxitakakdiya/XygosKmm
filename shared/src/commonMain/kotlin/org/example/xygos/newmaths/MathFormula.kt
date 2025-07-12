package org.example.xygos.newmaths

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class MathStep(
    val latex: String,
    val hint: String,
    val interaction: InteractionType = InteractionType.None,
    val target: String? = null,
    val choices: List<String> = emptyList()
)

enum class InteractionType {
    Tap,
    DragDrop,
    None
}

data class MathFormula(
    val id: String,
    val title: String,
    val latex: String,
    val category: String = "General",
    val steps: List<MathStep>? = null,
    val answer: String? = null
)
// 2. FormulaRepository.kt

object FormulaRepository {
    fun getAll(): List<MathFormula> = listOf(
        MathFormula(
            id = "simplify_1",
            title = "Simplify 3/4x - x",
            latex = "\\frac{3}{4}x - x",
            category = "Algebra",
            steps = listOf(
                MathStep(
                    latex = "\\frac{3}{4}x - x",
                    hint = "Convert x to a fraction",
                    interaction = InteractionType.Tap,
                    target = "4/4x",
                    choices = listOf("3/3x", "4/4x", "2/2x")
                ),
                MathStep(
                    latex = "\\frac{3}{4}x - \\frac{4}{4}x",
                    hint = "Now subtract the fractions",
                    interaction = InteractionType.Tap,
                    target = "-\\frac{1}{4}x",
                    choices = listOf("-\\frac{1}{4}x", "\\frac{7}{4}x", "0")
                ),
                MathStep(
                    latex = "-\\frac{1}{4}x",
                    hint = "Final answer",
                    interaction = InteractionType.None
                )
            ),
            answer = "-\\frac{1}{4}x"
        )
    )

    fun getByCategory(category: String): List<MathFormula> =
        getAll().filter { it.category == category }
}

// 3. MathView.kt (expect declaration remains unchanged)

@Composable
expect fun MathViewold(formula: String, modifier: Modifier = Modifier)

expect class MathView() {
    fun renderEquation(equation: String)
    fun setOnPartClickListener(listener: (String) -> Unit)
}