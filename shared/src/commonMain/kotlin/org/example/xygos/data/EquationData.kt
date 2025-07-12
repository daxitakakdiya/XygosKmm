package org.example.xygos.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.xygos.domain.EquationSolver

class EquationRepository {
    private val solver = EquationSolver()

    fun getAllEquations() = solver.getAvailableEquations()
    fun getEquationSteps(equation: String) = solver.getStepsForEquation(equation)
}
