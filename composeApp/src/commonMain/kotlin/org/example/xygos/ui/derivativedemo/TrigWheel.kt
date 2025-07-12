package org.example.xygos.ui.derivativedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.xygos.theme.AppColors
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin



@Composable
fun TrigWheel(
    correctAnswer: String,
    onCorrect: () -> Unit,
    onIncorrect: () -> Unit,
    showError: Boolean,
) {
    val functions = listOf("sin", "-sin", "cos", "-cos")
    val radius = 60.dp
    val density = LocalDensity.current

    var rotationAngle by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val currentIndex = ((rotationAngle / 90f).roundToInt() + 4) % 4

    Row(verticalAlignment = Alignment.CenterVertically) {
        // Derivative operator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text("d", fontSize = 16.sp, color = AppColors.White)
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(22.dp)
                    .background(AppColors.White)
            )
            Text("dθ", fontSize = 16.sp, color = AppColors.White)
        }

        // Left bracket
        Text("[", fontSize = 40.sp, color = AppColors.White, modifier = Modifier.padding(end = 4.dp))

        // The wheel with smooth rotation
        Box(
            modifier = Modifier
                .size(220.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { isDragging = true },
                        onDragEnd = {
                            isDragging = false
                            val targetAngle = (rotationAngle / 90f).roundToInt() * 90f
                            rotationAngle = targetAngle
                            val newIndex = ((targetAngle / 90f).roundToInt() + 4) % 4
                            if (functions[newIndex] == correctAnswer) {
                                onCorrect()
                            } else {
                                onIncorrect()
                            }
                        }
                    ){ _, dragAmount ->
                        val (dx, dy) = dragAmount
                        val dragRotation = (dx + dy) * 0.5f
                        rotationAngle += dragRotation
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            // Wheel border
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(Color.Transparent, shape = CircleShape)
                    .border(
                        2.dp,
                        color = if (showError) AppColors.Red else AppColors.White,
                        shape = CircleShape
                    )
            )
            val positions = listOf(270f, 0f, 90f, 180f) // Top, Right, Bottom, Left

            for (i in 0..3) {
                val angle = positions[i] + rotationAngle
                val radians = (angle * PI / 180f).toFloat()
                val x = cos(radians)
                val y = sin(radians)

                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (x * with(density) { radius.toPx() }).roundToInt(),
                                (y * with(density) { radius.toPx() }).roundToInt()
                            )
                        }
                        .size(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        functions[i],
                        color = AppColors.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

// Top selected function box (remains unchanged)
            val yOffsetPx = -with(density) { radius.toPx() } - 3f
            val yOffsetDp = with(density) { yOffsetPx.toDp() }
            Box(
                modifier = Modifier
                    .offset(y = yOffsetDp)
                    .width(80.dp)
                    .height(40.dp)
                    .border(
                        width = 2.dp,
                        color = if (showError) AppColors.Red else AppColors.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(top = 5.dp)
                    .background(Color.Transparent, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
//                Text(
//                    functions[currentIndex],
//                    color = AppColors.White,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold
//                )
            }
        }

        // Right bracket
        Text("]", fontSize = 40.sp, color = AppColors.White, modifier = Modifier.padding(start = 4.dp))
    }
}