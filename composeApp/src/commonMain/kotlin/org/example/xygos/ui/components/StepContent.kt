package org.example.xygos.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.xygos.model.EquationStep
import org.example.xygos.model.InteractionType




@Composable
fun StepContent(
    step: EquationStep,
    isCurrentStep: Boolean,
    onStepComplete: () -> Unit
) {
    Column(Modifier.padding(vertical = 12.dp)) {
        // Hint box
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFBBDEFB), Color(0xFF90CAF9))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = step.hint,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // Equation parts
        Row(verticalAlignment = Alignment.CenterVertically) {
            step.equationParts.forEach { part ->
                if (isCurrentStep &&
                    step.interactionType is InteractionType.Tap &&
                    part == step.expectedAnswer) {
                    TapToSolveOperator(part, onStepComplete)
                } else {
                    Text(
                        text = part,
                        fontSize = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }

        // Drag drop interaction
        if (isCurrentStep && step.interactionType is InteractionType.DragDrop) {
            Spacer(Modifier.height(8.dp))
            DragDropOptionBox(
                options = step.options,
                correctAnswer = step.expectedAnswer ?: ""
            ) { isCorrect ->
                if (isCorrect) onStepComplete()
            }
        }
    }
}

@Composable
fun TapToSolveOperator(part: String, onTapSuccess: () -> Unit) {
    val alpha by rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 0.5f,
            targetValue = 0.3f,
            animationSpec = infiniteRepeatable(
                animation = tween(700), repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )

    Box(
        modifier = Modifier.clickable { onTapSuccess() }.padding(8.dp),
         Alignment.Center
    ) {

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.Blue.copy(alpha = alpha), CircleShape)
            )
            // Text part (e.g., "−" or "4")
            Text(
                text = part,
                color = Color.Blue,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
    }
}

@Composable
fun DragDropOptionBox(
    options: List<String>,
    correctAnswer: String,
    onResult: (Boolean) -> Unit
) {
    var selected by remember { mutableStateOf<String?>(null) }

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        options.forEach { option ->
            Box(
                modifier = Modifier
                    .background(
                        color = when {
                            selected == null -> Color.LightGray
                            selected == option && option == correctAnswer -> Color.Green
                            selected == option -> Color.Red
                            else -> Color.LightGray
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        selected = option
                        onResult(option == correctAnswer)
                    }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(option, fontSize = 18.sp)
            }
        }
    }
}

