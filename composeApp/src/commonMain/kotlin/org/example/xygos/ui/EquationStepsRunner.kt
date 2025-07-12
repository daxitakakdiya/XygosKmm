package org.example.xygos.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import org.example.xygos.model.EquationStep
import org.example.xygos.model.InteractionType
import org.example.xygos.ui.components.StepContent

@Composable
fun EquationStepsRunner(
    equation: String,
    steps: List<EquationStep>,
    onBack: () -> Unit
) {
    var currentStep by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Back button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onBack() }
                .padding(bottom = 16.dp)
        ) {
            // Properly aligned back arrow
            Text(
                text = "←",
                fontSize = 20.sp, // Same size as "Back" text
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.offset(y = (-1).dp) // Fine-tune vertical alignment
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Back", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(Modifier.height(8.dp))
        Text(equation, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(8.dp))

        // Steps rendering
        steps.take(currentStep + 1).forEachIndexed { index, step ->
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(500)
                )
            ) {
                StepContent(
                    step = step,
                    isCurrentStep = index == currentStep,
                    onStepComplete = { currentStep++ }
                )
            }
        }

        // Final answer
        AnimatedVisibility(
            visible = currentStep == steps.lastIndex &&
                    steps[currentStep].interactionType is InteractionType.None,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(500)
            )
        ) {
            FinalAnswer(steps.last().equationParts.first())
        }
    }
}

@Composable
fun FinalAnswer(answer: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFD6F5D6), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text("Answer: $answer", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}


