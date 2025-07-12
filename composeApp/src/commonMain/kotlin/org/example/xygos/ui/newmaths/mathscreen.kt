package org.example.xygos.ui.newmaths

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.xygos.model.EquationStep
import org.example.xygos.model.EquationStep2
import org.example.xygos.model.InteractionType
import org.example.xygos.newmaths.MathRenderer
//@Composable
//fun MathScreen(mathRenderer: MathRenderer) {
//    var currentStepIndex by remember { mutableStateOf(0) }
//    val scrollState = rememberScrollState()
//
//    val equationSteps = remember {
//        listOf(
//            EquationStep2(
//                id = 1,
//                equationLatex = "\\frac{3}{4}x - x",
//                hint = "Convert x to a fraction with denominator 4",
//                interactionType = InteractionType.Tap
//            ),
//            EquationStep2(
//                id = 2,
//                equationLatex = "\\frac{3}{4}x - \\frac{4}{4}x",
//                hint = "Rewrite x as 4/4x",
//                interactionType = InteractionType.Tap
//            ),
//            EquationStep2(
//                id = 3,
//                equationLatex = "\\left(\\frac{3}{4} - \\frac{4}{4}\\right)x",
//                hint = "Subtract the coefficients",
//                interactionType = InteractionType.DragDrop,
//                options = listOf("\\frac{1}{4}", "-\\frac{1}{4}", "\\frac{7}{4}")
//            ),
//            EquationStep2(
//                id = 4,
//                equationLatex = "-\\frac{1}{4}x",
//                hint = "Final simplified form",
//                interactionType = InteractionType.None
//            )
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .verticalScroll(scrollState) // Enable scrolling
//    ) {
//        // Display all steps up to current index
//        equationSteps.take(currentStepIndex + 1).forEachIndexed { index, step ->
//            key(step.id) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp)
//                ) {
//                    // Hint with gradient background
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                brush = Brush.verticalGradient(
//                                    colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))
//                                )
//                            ).padding(16.dp)) {
//                                Text(
//                                    text = step.hint,
//                                    style = MaterialTheme.typography.bodyLarge.copy(
//                                        fontWeight = FontWeight.Bold,
//                                        color = Color.Black
//                                    )
//                                )
//                            }
//
//                                Spacer(modifier = Modifier.height(16.dp))
//
//                                // Equation display
//                                Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 8.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        mathRenderer.RenderEquation(step.equationLatex)
//                    }
//
//                    // Interaction area (only for active step)
//                    if (index == currentStepIndex) {
//                        when (step.interactionType) {
//                            InteractionType.Tap -> {
//                                Button(
//                                    onClick = {
//                                        if (currentStepIndex < equationSteps.size - 1) {
//                                            currentStepIndex++
//                                        }
//                                    },
//                                    modifier = Modifier
//                                        .align(Alignment.End)
//                                        .padding(top = 16.dp)
//                                ) {
//                                    Text("Continue")
//                                }
//                            }
//                            InteractionType.DragDrop -> {
//                                // Drag drop implementation
//                            }
//                            InteractionType.None -> {}
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(24.dp))
//            }
//        }
//    }
//}

@Composable
fun MathScreen(mathRenderer: MathRenderer) {
    var currentStepIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showFeedback by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val equationSteps = remember {
        listOf(
            EquationStep2(
                id = 0,
                equationLatex = "\\frac{3}{4}x - x",
                hint = "Convert x to a fraction with denominator 4",
                interactionType = InteractionType.Tap
            ),
            EquationStep2(
                id = 1,
                equationLatex = "\\frac{3}{4}x - \\frac{4}{4}x",
                hint = "Rewrite x as 4/4x",
                interactionType = InteractionType.Tap
            ),
            EquationStep2(
                id = 2,
                equationLatex = "\\left(\\frac{3}{4} - \\frac{4}{4}\\right)x",
                hint = "Subtract the coefficients",
                interactionType = InteractionType.Tap,
//                options = listOf("\\frac{1}{4}", "-\\frac{1}{4}", "\\frac{7}{4}")
            ),
            EquationStep2(
                id = 3,
                equationLatex = "-\\frac{1}{4}x",
                hint = "Final simplified form",
                interactionType = InteractionType.None
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Display all steps up to current index
        equationSteps.take(currentStepIndex + 1).forEachIndexed { index, step ->
            key(step.id) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    // Hint with gradient background
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))
                                )
                            ).padding(16.dp)
                    ) {
                        Text(
                            text = step.hint,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Interactive Equation Display
                    if (index == currentStepIndex && step.interactionType == InteractionType.Tap) {
                        // Tappable equation with clear visual feedback
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    // Direct tap on equation to proceed
                                    if (currentStepIndex < equationSteps.size-1 ) {
                                        currentStepIndex++
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFF9C4)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                mathRenderer.RenderEquation(step.equationLatex)

                                // Tap indicator
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .background(
                                            Color(0xFF4CAF50),
                                            shape = CircleShape
                                        )
                                        .size(28.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "👆",
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    } else {
                        // Non-interactive equation
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            mathRenderer.RenderEquation(step.equationLatex)
                        }
                    }

                    // Interaction area (only for active step)
                    if (index == currentStepIndex) {
                        when (step.interactionType) {
                            InteractionType.Tap -> {
                                // Visual indication with pulsing animation
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.align(Alignment.Center),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            "👆 ",
                                            fontSize = 18.sp
                                        )
                                        Text(
                                            "Tap the equation above to continue",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color(0xFF1976D2)
                                            )
                                        )
                                    }
                                }
                            }

                            InteractionType.DragDrop -> {
                                Spacer(modifier = Modifier.height(16.dp))

                                // Drag & Drop Options
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    contentPadding = PaddingValues(horizontal = 16.dp)
                                ) {
                                    items(step.options ?: emptyList()) { option ->
                                        Card(
                                            modifier = Modifier
                                                .clickable {
                                                    selectedAnswer = option
                                                    showFeedback = true

                                                    // Check if correct answer
                                                    if (option == "-\\frac{1}{4}") {
                                                        // Correct answer - proceed after short delay
                                                        kotlinx.coroutines.MainScope().launch {
                                                            delay(1500)
                                                            if (currentStepIndex < equationSteps.size - 1) {
                                                                currentStepIndex++
                                                                selectedAnswer = null
                                                                showFeedback = false
                                                            }
                                                        }
                                                    }
                                                }
                                                .padding(4.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = when {
                                                    selectedAnswer == option && option == "-\\frac{1}{4}" -> Color(0xFF4CAF50) // Green for correct
                                                    selectedAnswer == option -> Color(0xFFFF5722) // Red for incorrect
                                                    else -> Color(0xFFE1F5FE) // Default light blue
                                                }
                                            ),
                                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier.padding(16.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                mathRenderer.RenderEquation(option)
                                            }
                                        }
                                    }
                                }

                                // Feedback
                                if (showFeedback && selectedAnswer != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = if (selectedAnswer == "-\\frac{1}{4}") "✅ Correct!" else "❌ Try again",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = if (selectedAnswer == "-\\frac{1}{4}") Color(0xFF4CAF50) else Color(0xFFFF5722)
                                        ),
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }

                            InteractionType.None -> {
                                // Final step - show completion
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Color(0xFFE8F5E8),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        "🎉 Problem Solved!",
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2E7D32)
                                        ),
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
@Composable
fun EquationStepView(
    step: EquationStep2,
    isActive: Boolean,
    mathRenderer: MathRenderer,
    onComplete: () -> Unit
) {
    val borderColor = if (isActive) Color.Blue else Color.Transparent

    Column(
        modifier = Modifier
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Render LaTeX equation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            mathRenderer.RenderEquation(step.equationLatex)
        }

        // Hint text
        Text(
            text = step.hint,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Interaction area
        when (step.interactionType) {
            InteractionType.Tap -> {
                if (isActive) {
                    Button(
                        onClick = onComplete,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Continue")
                    }
                }
            }

            InteractionType.DragDrop -> {
                if (isActive) {
                    Column {
                        step.options?.forEach { option ->
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        if (option == "-\\frac{1}{4}") {
                                            onComplete()
                                        }
                                    }
                                    .padding(8.dp)
                            ) {
                                mathRenderer.RenderEquation(option)
                            }
                        }
                    }
                }
            }

            InteractionType.None -> {
                // No interaction for final step
            }
        }
    }
}
//@Composable
//fun MathScreen() {
//    val mathRenderer = remember { MathRenderer("#000000", "#FFFFFF") }
//    var maxVisibleStep by remember { mutableStateOf(0) } // Tracks highest shown step
//
//    val allSteps = listOf(
//        "Step 1: Original" to "\\lim_{x \\to 1} \\frac{\\sqrt{x+8}-3}{x-1}",
//        "Step 2: Multiply Conjugate" to "\\frac{\\sqrt{x+8}-3}{x-1} \\cdot \\frac{\\color{blue}{\\sqrt{x+8}+3}}{\\color{blue}{\\sqrt{x+8}+3}}",
//        "Step 3: Simplify" to "\\frac{x-1}{(x-1)(\\sqrt{x+8}+3)}",
//        "Step 4: Final Answer" to "\\frac{1}{6}"
//    )
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .clickable {
//                if (maxVisibleStep < allSteps.size - 1) {
//                    maxVisibleStep += 1
//                }
//            }
//    ) {
//        // Header
//        Row(verticalAlignment = Alignment.CenterVertically) {
////            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//            Spacer(Modifier.width(8.dp))
//            Text("Step ${maxVisibleStep + 1}/${allSteps.size}",
//                style = MaterialTheme.typography.bodyMedium)
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        // Display all steps up to maxVisibleStep
//        allSteps.take(maxVisibleStep + 1).forEachIndexed { index, (title, equation) ->
//            key(index) {
//                Column(
//                    modifier = Modifier.padding(vertical = 8.dp)
//                ) {
//                    Text(title,
//                        style = MaterialTheme.typography.titleSmall.copy(
//                            fontWeight = FontWeight.Bold
//                        )
//                    )
//                    Spacer(Modifier.height(4.dp))
//                    mathRenderer.RenderEquation(equation)
//                }
//            }
//        }
//
//        // Show hint if more steps available
//        if (maxVisibleStep < allSteps.size - 1) {
//            Text(
//                "↓ Tap anywhere to show next step ↓",
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
//                modifier = Modifier.padding(top = 16.dp)
//            )
//        }
//    }
//}