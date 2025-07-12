package org.example.xygos.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.xygos.data.EquationRepository

@Composable
fun EquationApp() {
    val repository = remember { EquationRepository() }
    var selectedEquation by remember { mutableStateOf<String?>(null) }

    if (selectedEquation == null) {
        EquationListScreen(
            equations = repository.getAllEquations(),
            onEquationSelected = { selectedEquation = it }
        )
    } else {
        val steps = repository.getEquationSteps(selectedEquation!!)
        EquationStepsRunner(
            equation = selectedEquation!!,
            steps = steps,
            onBack = { selectedEquation = null }
        )
    }

}