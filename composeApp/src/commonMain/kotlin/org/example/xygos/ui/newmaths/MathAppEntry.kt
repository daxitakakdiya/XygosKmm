package org.example.xygos.ui.newmaths

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.xygos.newmaths.FormulaRepository
import org.example.xygos.newmaths.InteractionType
import org.example.xygos.newmaths.MathFormula
import org.example.xygos.newmaths.MathStep
import org.example.xygos.newmaths.MathViewold
@Composable
fun GuidedPracticeScreen(formula: MathFormula, onBack: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }
    val steps = formula.steps ?: emptyList()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("← Back", modifier = Modifier.clickable { onBack() }, fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))
        Text(formula.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        MathViewold(formula = formula.latex, modifier = Modifier.fillMaxWidth().height(80.dp))
        Spacer(Modifier.height(16.dp))

        steps.take(currentStep + 1).forEachIndexed { index, step ->
            StepCard(step = step, isCurrent = index == currentStep) {
                currentStep++
            }
        }

        if (currentStep >= steps.size) {
            Text("✔ Answer: ${formula.answer}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF388E3C), modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun StepCard(step: MathStep, isCurrent: Boolean, onSuccess: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Hint: ${step.hint}", fontSize = 16.sp)
        MathViewold(formula = step.latex, modifier = Modifier.fillMaxWidth().height(60.dp))

        when (step.interaction) {
            InteractionType.Tap -> {
                if (isCurrent) {
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        step.choices.forEach { choice ->
                            Button(
                                onClick = {
                                    if (choice == step.target) onSuccess()
                                },
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text(choice)
                            }
                        }
                    }
                }
            }
            else -> {
                Text("✅ Step complete", color = Color(0xFF4CAF50), fontSize = 14.sp)
            }
        }
    }
}

// 5. IndependentPracticeScreen.kt (unchanged)

@Composable
fun IndependentPracticeScreen(formula: MathFormula, onBack: () -> Unit) {
    var userAnswer by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("← Back", modifier = Modifier.clickable { onBack() }, fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))
        Text(formula.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        MathViewold(formula = formula.latex, modifier = Modifier.fillMaxWidth().height(80.dp))
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Your Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = { submitted = true }, modifier = Modifier.align(Alignment.End)) {
            Text("Submit")
        }

        if (submitted) {
            if (userAnswer.replace(" ", "") == formula.answer?.replace(" ", "")) {
                Text("✔ Correct!", color = Color(0xFF388E3C), fontSize = 18.sp)
            } else {
                Text("❌ Try again or review steps.", color = Color.Red, fontSize = 18.sp)
                Text("Answer: ${formula.answer}", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}

// 6. PracticeModeSelector.kt (unchanged)

enum class PracticeMode { Guided, Independent }

@Composable
fun PracticeModeScreen(onSelect: (MathFormula, PracticeMode) -> Unit) {
    val formulas = FormulaRepository.getAll()
    val selectedFormula = formulas.first() // could be selected via UI later

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Select Practice Mode", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(16.dp))

        Button(onClick = { onSelect(selectedFormula, PracticeMode.Guided) }, modifier = Modifier.fillMaxWidth()) {
            Text("Guided Mode")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = { onSelect(selectedFormula, PracticeMode.Independent) }, modifier = Modifier.fillMaxWidth()) {
            Text("Independent Mode")
        }
    }
}

// 7. Entry.kt (unchanged)

@Composable
fun MathAppEntry() {
    var currentScreen by remember { mutableStateOf<Pair<MathFormula, PracticeMode>?>(null) }

    if (currentScreen == null) {
        PracticeModeScreen(onSelect = { formula, mode ->
            currentScreen = formula to mode
        })
    } else {
        val (formula, mode) = currentScreen!!
        when (mode) {
            PracticeMode.Guided -> GuidedPracticeScreen(formula, onBack = { currentScreen = null })
            PracticeMode.Independent -> IndependentPracticeScreen(formula, onBack = { currentScreen = null })
        }
    }
}
