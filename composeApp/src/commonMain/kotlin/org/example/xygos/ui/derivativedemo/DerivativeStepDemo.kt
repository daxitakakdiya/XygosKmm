package org.example.xygos.ui.derivativedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.xygos.model.DerivativeStep
import org.example.xygos.model.DerivativeStepType
import org.example.xygos.theme.AppColors
@Composable
fun XygosDerivativeStepDemo() {
    val steps = listOf(
        DerivativeStep(DerivativeStepType.Start, "", actionable = true),
        DerivativeStep(
            DerivativeStepType.HighlightInner,
            "Rotate the wheel for the sine rule",
            actionable = true
        ),
        DerivativeStep(
            DerivativeStepType.TrigWheel,
            "Rotate the wheel for the sine rule",
            actionable = true
        ),
        DerivativeStep(
            DerivativeStepType.ChainRule,
            "Drag to apply the chain rule",
            actionable = true
        ),
        DerivativeStep(
            DerivativeStepType.ConstantOut,
            "Drag the constant out of the derivative",
            actionable = true
        ),
        DerivativeStep(DerivativeStepType.Final, "", actionable = false),
        DerivativeStep(DerivativeStepType.Summary, "", actionable = false)
    )

    var showError by remember { mutableStateOf(false) }
    val correctAnswer = "cos"
    var revealedSteps by remember { mutableStateOf(2) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Derivative Solution",
            fontSize = 22.sp,
            color = AppColors.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        steps.take(revealedSteps).forEachIndexed { idx, step ->
            val isCurrent = idx == revealedSteps - 1 && revealedSteps < steps.size

            // Only show the TrigWheel card if it is the current step
            if (step.type != DerivativeStepType.TrigWheel || isCurrent) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .then(
                            if (
                                step.actionable &&
                                isCurrent &&
                                step.type != DerivativeStepType.TrigWheel
                            ) {
                                Modifier.pointerInput(Unit) {
                                    detectTapGestures {
                                        revealedSteps++
                                    }
                                }
                            } else Modifier
                        ),
                    colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // HINT AT TOP
                        if (step.hint.isNotBlank() && isCurrent) {
                            Text(
                                step.hint,
                                fontSize = 16.sp,
                                color = AppColors.Warning,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(Modifier.height(8.dp))
                        }

                        // MATH CONTENT
                        when (step.type) {
                            DerivativeStepType.Start -> DerivativeMathExpression(showHint = isCurrent)
                            DerivativeStepType.HighlightInner -> DerivativeMathExpression(
                                highlightInner = true,
                                showHint = isCurrent
                            )
                            DerivativeStepType.TrigWheel -> TrigWheel(
                                correctAnswer = correctAnswer,
                                onCorrect = { revealedSteps++ },
                                onIncorrect = {
                                    showError = true
                                    coroutineScope.launch {
                                        delay(500)
                                        showError = false
                                    }
                                },
                                showError = showError
                            )
                            DerivativeStepType.ChainRule -> ChainRuleStep(showHint = isCurrent)
                            DerivativeStepType.ConstantOut -> ConstantOutStep(showHint = isCurrent)
                            DerivativeStepType.Final -> FinalStep()
                            DerivativeStepType.Summary -> SummaryStep()
                            DerivativeStepType.ExpandSum -> TODO()
                            DerivativeStepType.ProductRule -> TODO()
                            DerivativeStepType.DerivativeFirst -> TODO()
                            DerivativeStepType.DerivativeSecond -> TODO()
                            DerivativeStepType.DerivativeEachTerm -> TODO()
                            DerivativeStepType.Combine -> TODO()
                        }
                    }
                }
            }
        }
    }
}





//@Composable
//fun XygosDerivativeStepDemo() {
//    val steps = listOf(
//        DerivativeStep(DerivativeStepType.Start, "", actionable = true),
//        DerivativeStep(
//            DerivativeStepType.HighlightInner,
//            "Rotate the wheel for the sine rule",
//            actionable = true
//        ),
//        DerivativeStep(
//            DerivativeStepType.TrigWheel,
//            "Rotate the wheel for the sine rule",
//            actionable = true
//        ),
//        DerivativeStep(
//            DerivativeStepType.ChainRule,
//            "Drag to apply the chain rule",
//            actionable = true
//        ),
//        DerivativeStep(
//            DerivativeStepType.ConstantOut,
//            "Drag the constant out of the derivative",
//            actionable = true
//        ),
//        DerivativeStep(DerivativeStepType.Final, "", actionable = false),
//        DerivativeStep(DerivativeStepType.Summary, "", actionable = false)
//    )
//
//    var showError by remember { mutableStateOf(false) }
//    val correctAnswer = "cos"
//    var revealedSteps by remember { mutableStateOf(2) }
//    var trigWheelIndex by remember { mutableStateOf(0) }
//    val coroutineScope = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(AppColors.Background)
//            .padding(12.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            "Derivative Solution",
//            fontSize = 22.sp,
//            color = AppColors.White,
//            fontWeight = FontWeight.Bold
//        )
//        Spacer(Modifier.height(12.dp))
//
//        steps.take(revealedSteps).forEachIndexed { idx, step ->
//            val isCurrent = idx == revealedSteps - 1 && revealedSteps < steps.size
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//                    .then(
//                        if (step.actionable && idx == revealedSteps - 1 && revealedSteps < steps.size)
//                            Modifier.pointerInput(Unit) {
//                                detectTapGestures {
//                                    // For trig wheel, only proceed if correct function is selected
//                                    if (step.type == DerivativeStepType.TrigWheel && trigWheelIndex != 0) return@detectTapGestures
//                                    revealedSteps++
//                                }
//                            }
//                        else Modifier
//                    ),
//                colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    // HINT AT TOP
//                    if (step.hint.isNotBlank() && isCurrent) {
//                        Text(
//                            step.hint,
//                            fontSize = 16.sp,
//                            color = AppColors.Warning,
//                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                        )
//                        Spacer(Modifier.height(8.dp))
//                    }
//
//                    // MATH CONTENT
//                    when (step.type) {
//                        DerivativeStepType.Start -> DerivativeMathExpression(showHint = isCurrent)
//                        DerivativeStepType.HighlightInner -> DerivativeMathExpression(
//                            highlightInner = true,
//                            showHint = isCurrent
//                        )
//                        DerivativeStepType.TrigWheel -> TrigWheel(
//                            correctAnswer = correctAnswer,
//                            onCorrect = { revealedSteps++ },
//                            onIncorrect = {
//                                showError = true
//                                coroutineScope.launch {
//                                    delay(500)
//                                    showError = false
//                                }
//                            },
//                            showError = showError
//                        )
//
//                        DerivativeStepType.ChainRule -> ChainRuleStep(showHint = isCurrent)
//                        DerivativeStepType.ConstantOut -> ConstantOutStep(showHint = isCurrent)
//                        DerivativeStepType.Final -> FinalStep()
//                        DerivativeStepType.Summary -> SummaryStep()
//                    }
//                }
//            }
//        }
//    }
//}