package org.example.xygos.domain

import org.example.xygos.model.EquationStep
import org.example.xygos.model.InteractionType

class EquationSolver {
    fun getStepsForEquation(equation: String): List<EquationStep> {
        return when (equation) {
            "x² − 16" -> listOf(
                EquationStep(1, listOf("x²", "−", "16"), "Recognize difference of squares", InteractionType.Tap, "−"),
                EquationStep(2, listOf("x²", "−", "4²"), "What is square root of 16?", InteractionType.DragDrop, "4", listOf("2", "3", "4")),
                EquationStep(3, listOf("(x − 4)", "(x + 4)"), "Apply identity a²−b²=(a−b)(a+b)", InteractionType.Tap, "(x − 4)"),
                EquationStep(4, listOf("(x − 4)(x + 4)"), "Factored form of x² − 16", InteractionType.None)
            )
            "a² − 9" -> listOf(
                EquationStep(1, listOf("a²", "−", "9"), "Recognize difference of squares", InteractionType.Tap, "−"),
                EquationStep(2, listOf("a²", "−", "3²"), "What is square root of 9?", InteractionType.DragDrop, "3", listOf("2", "3", "4")),
                EquationStep(3, listOf("(a − 3)", "(a + 3)"), "Apply identity a²−b²=(a−b)(a+b)", InteractionType.Tap, "(a − 3)"),
                EquationStep(4, listOf("(a − 3)(a + 3)"), "Factored form of a² − 9", InteractionType.None)
            )
            "3/4x - x" -> listOf(
                EquationStep(
                    id = 1,
                    equationParts = listOf("3/4x", "-", "x"),
                    hint = "Convert x to a fraction with denominator 4",
                    interactionType = InteractionType.Tap,
                    expectedAnswer = "x"
                ),
                EquationStep(
                    id = 2,
                    equationParts = listOf("3/4x", "-", "4/4x"),
                    hint = "Rewrite x as 4/4x",
                    interactionType = InteractionType.Tap,
                    expectedAnswer = "4/4x"
                ),
                EquationStep(
                    id = 3,
                    equationParts = listOf("(3/4 - 4/4)x"),
                    hint = "Subtract the coefficients: 3/4 - 4/4",
                    interactionType = InteractionType.DragDrop,
                    expectedAnswer = "-1/4",
                    options = listOf("1/4", "-1/4", "7/4")
                ),
                EquationStep(
                    id = 4,
                    equationParts = listOf("-1/4x"),
                    hint = "Final simplified form",
                    interactionType = InteractionType.None
                )
            )
            else -> emptyList()
        }
    }

    fun getAvailableEquations(): List<String> {
        return listOf("x² − 16", "a² − 9", "3/4x - x")
    }
}

//class EquationSolver {
//    fun getStepsForEquation(equation: String): List<EquationStep> = when (equation) {
//        "3/4x - x" -> listOf(
//            EquationStep(
//                id = 1,
//                equationParts = listOf("""\frac{3}{4}x""", "-", "x"),
//                hint = "Convert \\(x\\) to a fraction with denominator 4",
//                interactionType = InteractionType.Tap,
//                expectedAnswer = "x"
//            ),
//            EquationStep(
//                id = 2,
//                equationParts = listOf("""\frac{3}{4}x""", "-", """\frac{4}{4}x"""),
//                hint = "Rewrite \\(x\\) as \\(\\frac{4}{4}x\\)",
//                interactionType = InteractionType.Tap,
//                expectedAnswer = """\frac{4}{4}x"""
//            ),
//            EquationStep(
//                id = 3,
//                equationParts = listOf("""\left(\frac{3}{4} - \frac{4}{4}\right)x"""),
//                hint = "Subtract the coefficients: \\(\\frac{3}{4} - \\frac{4}{4}\\)",
//                interactionType = InteractionType.DragDrop,
//                expectedAnswer = """-\frac{1}{4}""",
//                options = listOf("""\frac{1}{4}""", """-\frac{1}{4}""", """\frac{7}{4}""")
//            ),
//            EquationStep(
//                id = 4,
//                equationParts = listOf("""-\frac{1}{4}x"""),
//                hint = "Final simplified form",
//                interactionType = InteractionType.None
//            )
//        )
//        else -> emptyList()
//    }
//}