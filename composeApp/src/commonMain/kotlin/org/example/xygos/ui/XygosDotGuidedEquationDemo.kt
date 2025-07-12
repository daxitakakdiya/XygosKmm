package org.example.xygos.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.xygos.model.DotPosition
import org.example.xygos.model.MathToken


//@Composable
//fun XygosDotGuidedEquationDemo() {
//    // Step data: tokens, dot index, dot position
//    val steps = listOf(
//        // Step 0: 3/4x - x, dot above '-'
//        Triple(
//            listOf(
//                MathToken.Fraction(
//                    numerator = listOf(MathToken.Plain("3")),
//                    denominator = listOf(MathToken.Plain("4"))
//                ),
//                MathToken.Variable("x"),
//                MathToken.Operator("-"),
//                MathToken.Variable("x")
//            ),
//            2, // index of '-'
//            DotPosition.ABOVE
//        ),
//        Triple(
//            listOf(
//                MathToken.Fraction(
//                    numerator = listOf(MathToken.Plain("3")),
//                    denominator = listOf(MathToken.Plain("4"))
//                ),
//                MathToken.Variable("x"),
//                MathToken.Operator("-"),
//                MathToken.Fraction(
//                    numerator = listOf(MathToken.Plain("4")),
//                    denominator = listOf(MathToken.Plain("4"))
//                ),
//                MathToken.Variable("x")
//            ),
//            2, // index of '-'
//            DotPosition.ABOVE
//        ),
//        Triple(
//            listOf(
//                MathToken.Fraction(
//                    numerator = listOf(
//                        MathToken.LeftBracket,
//                        MathToken.Plain("3"),
//                        MathToken.Operator("-"),
//                        MathToken.Plain("4"),
//                        MathToken.RightBracket
//                    ),
//                    denominator = listOf(MathToken.Plain("4"))
//                ),
//                MathToken.Variable("x")
//            ),
//            -1, // No dot
//            DotPosition.ABOVE
//        ),
//        Triple(
//            listOf(
//                MathToken.Fraction(
//                    numerator = listOf(MathToken.Plain("-1")),
//                    denominator = listOf(MathToken.Plain("4"))
//                ),
//                MathToken.Variable("x")
//            ),
//            -1,
//            DotPosition.ABOVE
//        )
//    )
//
//    var currentStep by remember { mutableStateOf(0) }
//    val visibleSteps = remember { mutableStateListOf(0) }
//
//    // Animate new step in
//    LaunchedEffect(currentStep) {
//        if (!visibleSteps.contains(currentStep)) {
//            delay(200) // slight delay for realism
//            visibleSteps.add(currentStep)
//        }
//    }
//
//    Column(
//        Modifier
//            .fillMaxSize()
//            .padding(4.dp),
////        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
////        Text("Solve: 3/4x - x", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 24.dp))
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(bottom = 24.dp)
//        ) {
//            Text("Solve: ", style = MaterialTheme.typography.headlineMedium)
//            Spacer(Modifier.width(8.dp))
//            MathRowWithDot(
//                tokens = listOf(
//                    MathToken.Fraction(
//                        numerator = listOf(MathToken.Plain("3")),
//                        denominator = listOf(MathToken.Plain("4"))
//                    ),
//                    MathToken.Variable("x"),
//                    MathToken.Operator("-"),
//                    MathToken.Variable("x")
//                ),
//                dotIndex = -1, // No dot for the header
//                dotPosition = DotPosition.ABOVE
//            )
//        }
//        visibleSteps.forEach { stepIdx ->
//            val showDot = stepIdx == currentStep
//            val dotIndex = if (showDot) steps[stepIdx].second else -1
//            val dotPosition = if (showDot) steps[stepIdx].third else DotPosition.ABOVE // position doesn't matter if dotIndex==-1
//
//            AnimatedVisibility(
//                visible = stepIdx in visibleSteps,
//                enter = slideInVertically(
//                    animationSpec = tween(200),
//                    initialOffsetY = { it / 2 }
//                ) + fadeIn(animationSpec = tween(200)),
//                exit = fadeOut(animationSpec = tween(300)),
//                modifier = Modifier.padding(vertical = 8.dp)
//            ) {
//                Column (
////                    horizontalAlignment = Alignment.CenterHorizontally
//                ){
//                    when (stepIdx) {
//                        0 -> MathRowWithDot(
//                            tokens = steps[0].first,
//                            dotIndex = dotIndex,
//                            dotPosition = dotPosition,
//                            onTap = { idx ->
//                                if (idx == steps[0].second && currentStep == 0) {
//                                    currentStep = 1
//                                }
//                            }
//                        )
//
//                        1 -> MathRowWithDot(
//                            tokens = steps[1].first,
//                            dotIndex = dotIndex,
//                            dotPosition = dotPosition,
//                            onTap = { idx ->
//                                if (idx == steps[1].second && currentStep == 1) {
//                                    currentStep = 2
//                                }
//                            }
//                        )
//
//                        2 -> MathRowWithDot(
//                            tokens = steps[2].first,
//                            dotIndex = dotIndex,
//                            dotPosition = dotPosition
//                        )
//                        3 -> MathRowWithDot(
//                            tokens = steps[3].first,
//                            dotIndex = dotIndex,
//                            dotPosition = dotPosition
//                        )
//                    }
//
//                    if (stepIdx < currentStep) {
//                        Divider(
//                            color = Color.Gray,
//                            thickness = 2.dp,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                        )
//                    }
//                }
//            }
//        }
//
//        val coroutineScope = rememberCoroutineScope()
//        var selectedHint by remember { mutableStateOf<Int?>(null) }
//        var showFeedback by remember { mutableStateOf(false) }
//        val options = listOf("-1", "2", "0")
//        val denominator = "4"
//        val label = "3-4"
//        val variable = "x"
//
//        if (currentStep == 2) {
//
//            FractionWithBoxedOptionsAndLabelNumerator(
//                options = options,
//                selectedIndex = selectedHint,
//                onSelect = { idx ->
//                    selectedHint = idx
//                    showFeedback = true
//                    coroutineScope.launch {
//                        delay(100)
//                        if (options[idx] == "-1") {
//                            currentStep = 3 // advance to next step
//                        }
//                        selectedHint = null
//                        showFeedback = false
//                    }
//                },
//                label = label,
//                denominator = denominator,
//                variable = variable
//            )
//        }
//    }
//}
//@Composable
//fun FractionWithBoxedOptionsAndLabelNumerator(
//    options: List<String>,
//    selectedIndex: Int?,
//    onSelect: (Int) -> Unit,
//    label: String,
//    denominator: String,
//    variable: String // e.g., "x"
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically // Align x with the fraction line
//    ) {
//        // The fraction
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            // Numerator: One box containing options and label
//            Box(
//                modifier = Modifier
//                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//            ) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(12.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        options.forEachIndexed { idx, opt ->
//                            Box(
//                                modifier = Modifier
//                                    .size(36.dp)
//                                    .border(
//                                        2.dp,
//                                        if (selectedIndex == idx) {
//                                            if (options[idx] == "-1") Color(0xFF4CAF50) else Color.Red
//                                        } else Color.Gray,
//                                        RoundedCornerShape(6.dp)
//                                    )
//                                    .background(Color.Transparent, RoundedCornerShape(6.dp))
//                                    .clickable { onSelect(idx) },
//                                contentAlignment = Alignment.Center
//                            ) {
//                                Text(opt, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
//                            }
//                        }
//                    }
//                    // Label (e.g., 3-4)
//                    Text(
//                        label,
//                        color = Color.Black,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        modifier = Modifier.padding(top = 2.dp)
//                    )
//                }
//            }
//            Spacer(Modifier.height(5.dp))
//            // Fraction line
//            Box(
//                Modifier
//                    .height(2.dp)
//                    .width(120.dp)
//                    .background(Color.Black)
//            )
//            // Denominator centered
//            Text(
//                text = denominator,
//                color = Color.Black,
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.width(120.dp),
//                textAlign = TextAlign.Center
//            )
//        }
//        // x immediately to the right of the fraction line
//        Spacer(Modifier.width(8.dp))
//        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.Start
//        ) {
//            Spacer(Modifier.height(49.dp)) // Adjust this value to align x with the fraction line visually
//            Text(
//                variable,
//                color = Color.Black,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Medium
//            )
//        }
//    }
//}
//@Composable
//fun MathTokenView(token: MathToken) {
//    when (token) {
//        is MathToken.Fraction -> FractionView(token.numerator, token.denominator)
//        is MathToken.Variable -> Text(token.name, fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.Operator -> Text(token.symbol, fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.Plain -> Text(token.value, fontSize = 24.sp)
//        is MathToken.LeftBracket -> Text("(", fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.RightBracket -> Text(")", fontSize = 24.sp, fontWeight = FontWeight.Medium)
//    }
//}
//
//@Composable
//fun FractionView(numerator: List<MathToken>, denominator: List<MathToken>, dotIndex: Int? = null, dotPosition: DotPosition? = null, onTap: ((Int) -> Unit)? = null) {
//    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 2.dp)) {
//        Row {
//            numerator.forEachIndexed { idx, token ->
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .then(
//                            if (onTap != null && dotIndex == idx && dotPosition == DotPosition.ABOVE) {
//                                Modifier.clickable { onTap(idx) }
//                            } else Modifier
//                        )
//                ) {
//                    if (dotIndex == idx && dotPosition == DotPosition.ABOVE) {
//                        FlashingBlueDot()
//                        MathTokenView(token)
//                    } else {
//                        MathTokenView(token)
//                    }
//                }
//            }
//        }
//        Box(
//            Modifier
//                .height(2.dp)
//                .width(24.dp)
//                .background(Color.Black)
//        )
//        Row {
//            denominator.forEach { token ->
//                MathTokenView(token)
//            }
//        }
//    }
//}
//@Composable
//fun MathRowWithDot(
//    tokens: List<MathToken>,
//    dotIndex: Int,
//    dotPosition: DotPosition,
//    onTap: ((Int) -> Unit)? = null,
//    onDrag: ((Int) -> Unit)? = null
//) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        tokens.forEachIndexed { idx, token ->
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .padding(horizontal = 4.dp)
//                    .then(
//                        if (onTap != null && dotIndex == idx && dotPosition == DotPosition.ABOVE) {
//                            Modifier.clickable { onTap(idx) }
//                        } else if (onDrag != null && dotIndex == idx && dotPosition == DotPosition.BELOW) {
//                            Modifier.pointerInput(Unit) {
//                                detectDragGestures { _, dragAmount ->
//                                    if (dragAmount.y > 30f) { // drag down
//                                        onDrag(idx)
//                                    }
//                                }
//                            }
//                        } else Modifier
//                    )
//            ) {
//                if (dotIndex == idx && dotPosition == DotPosition.ABOVE) {
//                    FlashingBlueDot()
//                    MathTokenView(token)
//                } else if (dotIndex == idx && dotPosition == DotPosition.BELOW) {
//                    MathTokenView(token)
//                    FlashingBlueDot()
//                } else {
//                    MathTokenView(token)
//                }
//            }
//        }
//    }
//}
//@Composable
//fun FlashingBlueDot(
//    modifier: Modifier = Modifier,
//    size: Dp = 14.dp
//) {
//    val scale = remember { Animatable(1f) }
//
//    // Flash (scale) every 2 seconds
//    LaunchedEffect(Unit) {
//        while (true) {
//            scale.snapTo(1f)
//            delay(500) // wait 2 seconds
//            scale.animateTo(
//                targetValue = 1.5f,
//                animationSpec = tween(durationMillis = 200)
//            )
//            scale.animateTo(
//                targetValue = 1f,
//                animationSpec = tween(durationMillis = 200)
//            )
//        }
//    }
//
//    Box(
//        modifier = modifier
//            .size(size)
//            .graphicsLayer {
//                scaleX = scale.value
//                scaleY = scale.value
//            }
//            .background(Color(0xFF1976D2), CircleShape)
//    )
//}



// Data classes for equation definitions
data class EquationStep(
    val tokens: List<Any>,
    val dotIndex: Int = -1,
    val dotPosition: DotPosition = DotPosition.ABOVE,
    val interactiveOptions: InteractiveOptions? = null
)

data class InteractiveOptions(
    val options: List<String>,
    val correctAnswer: String,
    val label: String,
    val denominator: String,
    val variable: String
)

data class Equation(
    val title: String,
    val initialExpression: List<MathToken>,
    val steps: List<EquationStep>
)

// Define your equations here
object EquationRepository {
    val equations = listOf(

        Equation(
            title = "Solve: 3/4x - x",
            initialExpression = listOf(
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("3")),
                    denominator = listOf(MathToken.Plain("4"))
                ),
                MathToken.Variable("x"),
                MathToken.Operator("-"),
                MathToken.Variable("x")
            ),
            steps = listOf(
                // Step 1: Original expression
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("3")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Variable("x"),
                        MathToken.Operator("-"),
                        MathToken.Variable("x")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Start with the original expression"
                ),
                // Step 2: Convert x to fraction with common denominator
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("3")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Variable("x"),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("1"))
                        ),
                        MathToken.Variable("x")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Write x as 1/1 × x"
                ),
                // Step 3: Find common denominator
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("3")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Variable("x"),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("4")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Variable("x")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Convert to common denominator 4"
                ),
                // Step 4: Factor out x
                EquationStep(
                    tokens = listOf(
                        MathToken.LeftBracket,
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("3")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("4")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.RightBracket,
                        MathToken.Variable("x")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Factor out x"
                ),
                // Step 5: Combine fractions
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(
                                MathToken.LeftBracket,
                                MathToken.Plain("3"),
                                MathToken.Operator("-"),
                                MathToken.Plain("4"),
                                MathToken.RightBracket
                            ),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Variable("x")
                    ),
                    interactiveOptions = InteractiveOptions(
                        options = listOf("-1", "1", "7", "-7"),
                        correctAnswer = "-1",
                        label = "3-4",
                        denominator = "4",
                        variable = "x"
                    ),
//                    explanation = "Subtract numerators: 3 - 4"
                ),
                // Step 6: Final answer
                EquationStep(
                    tokens = listOf(
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("4"))
                        ),
                        MathToken.Variable("x")
                    ),
//                    explanation = "Final simplified form"
                )
            )
        ),

        // Equation 2: 1/2x = 3 (Complete 5-step solution)
        Equation(
            title = "Solve: 1/2x = 3",
            initialExpression = listOf(
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("1")),
                    denominator = listOf(MathToken.Plain("2"))
                ),
                MathToken.Variable("x"),
                MathToken.Operator("="),
                MathToken.Plain("3")
            ),
            steps = listOf(
                // Step 1: Original equation
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("2"))
                        ),
                        MathToken.Variable("x"),
                        MathToken.Operator("="),
                        MathToken.Plain("3")
                    ),
                    dotIndex = 0,
                    dotPosition = DotPosition.ABOVE
//                    explanation = "Start with the equation"
                ),
                // Step 2: Multiply both sides by 2
                EquationStep(
                    tokens = listOf(
                        MathToken.Plain("2"),
                        MathToken.Operator("×"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("2"))
                        ),
                        MathToken.Variable("x"),
                        MathToken.Operator("="),
                        MathToken.Plain("2"),
                        MathToken.Operator("×"),
                        MathToken.Plain("3")
                    ),
                    dotIndex = 1,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Multiply both sides by 2 to eliminate fraction"
                ),
                // Step 3: Simplify left side
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("2")),
                            denominator = listOf(MathToken.Plain("2"))
                        ),
                        MathToken.Variable("x"),
                        MathToken.Operator("="),
                        MathToken.Plain("2"),
                        MathToken.Operator("×"),
                        MathToken.Plain("3")
                    ),
                    dotIndex = 0,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "2 × 1/2 = 2/2"
                ),
                // Step 4: Reduce fraction and multiply right side
                EquationStep(
                    tokens = listOf(
                        MathToken.Variable("x"),
                        MathToken.Operator("="),
                        MathToken.Plain("6")
                    ),
                    interactiveOptions = InteractiveOptions(
                        options = listOf("6", "5", "8", "4"),
                        correctAnswer = "6",
                        label = "2×3",
                                denominator = "",
                        variable = ""
                    ),

//                    explanation = "2/2 = 1 and 2 × 3 = 6"
                ),
                // Step 5: Final answer
                EquationStep(
                    tokens = listOf(
                        MathToken.Variable("x"),
                        MathToken.Operator("="),
                        MathToken.Plain("6")
                    ),
//                    explanation = "Solution: x = 6"
                )
            )
        ),

        // Equation 3: 7/9a - 2/9a (Complete 4-step solution)
        Equation(
            title = "Solve: 7/9a - 2/9a",
            initialExpression = listOf(
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("7")),
                    denominator = listOf(MathToken.Plain("9"))
                ),
                MathToken.Variable("a"),
                MathToken.Operator("-"),
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("2")),
                    denominator = listOf(MathToken.Plain("9"))
                ),

                MathToken.Variable("a")
            ),
            steps = listOf(
                // Step 1: Original expression
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("7")),
                            denominator = listOf(MathToken.Plain("9"))
                        ),
                        MathToken.Variable("a"),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("2")),
                            denominator = listOf(MathToken.Plain("9"))
                        ),
                        MathToken.Variable("a")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Start with fractions that have same denominator"
                ),
                // Step 2: Factor out common variable
                EquationStep(
                    tokens = listOf(
                        MathToken.LeftBracket,
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("7")),
                            denominator = listOf(MathToken.Plain("9"))
                        ),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("2")),
                            denominator = listOf(MathToken.Plain("9"))
                        ),
                        MathToken.RightBracket,
                        MathToken.Variable("a")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Factor out variable a"
                ),
                // Step 3: Combine fractions
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(
                                MathToken.LeftBracket,
                                MathToken.Plain("7"),
                                MathToken.Operator("-"),
                                MathToken.Plain("2"),
                                MathToken.RightBracket
                            ),
                            denominator = listOf(MathToken.Plain("9"))
                        ),
                        MathToken.Variable("a")
                    ),
                    interactiveOptions = InteractiveOptions(
                        options = listOf("5", "9", "3", "4"),
                        correctAnswer = "5",
                        label = "7-2",
                        denominator = "9",
                        variable = "a"
                    ),
//                    explanation = "Subtract numerators: 7 - 2"
                ),
                // Step 4: Final answer
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("5")),
                            denominator = listOf(MathToken.Plain("9"))
                        ),
                        MathToken.Variable("a")
                    ),
//                    explanation = "Final simplified form"
                )
            )
        ),

        // Equation 4: 3/5b + 1/10b (Complete 6-step solution)
        Equation(
            title = "Solve: 3/5b + 1/10b",
            initialExpression = listOf(
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("3")),
                    denominator = listOf(MathToken.Plain("5"))
                ),
                MathToken.Variable("b"),
                MathToken.Operator("+"),
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("1")),
                    denominator = listOf(MathToken.Plain("10"))
                ),
                MathToken.Variable("b")
            ),
            steps = listOf(
//                // Step 1: Original expression
//                EquationStep(
//                    tokens = listOf(
//                        MathToken.Fraction(
//                            numerator = listOf(MathToken.Plain("3")),
//                            denominator = listOf(MathToken.Plain("5"))
//                        ),
//                        MathToken.Variable("b"),
//                        MathToken.Operator("+"),
//                        MathToken.Fraction(
//                            numerator = listOf(MathToken.Plain("1")),
//                            denominator = listOf(MathToken.Plain("10"))
//                        ),
//                        MathToken.Variable("b")
//                    ),
//                    dotIndex = 2,
//                    dotPosition = DotPosition.ABOVE,
////                    explanation = "Start with fractions having different denominators"
//                ),
                // Step 2: Find LCD (Least Common Denominator)
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("3")),
                            denominator = listOf(MathToken.Plain("5"))
                        ),
                        MathToken.Variable("b"),
                        MathToken.Operator("+"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("10"))
                        ),
                        MathToken.Variable("b")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "LCD of 5 and 10 is 10"
                ),
                // Step 3: Convert first fraction to common denominator
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("3"), MathToken.Operator("×"), MathToken.Plain("2")),
                            denominator = listOf(MathToken.Plain("5"), MathToken.Operator("×"), MathToken.Plain("2"))
                        ),
                        MathToken.Variable("b"),
                        MathToken.Operator("+"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("10"))
                        ),
                        MathToken.Variable("b")
                    ),
                    dotIndex = 0,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Multiply 3/5 by 2/2 to get denominator 10"
                ),
                // Step 4: Simplify first fraction
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("6")),
                            denominator = listOf(MathToken.Plain("10"))
                        ),
                        MathToken.Variable("b"),
                        MathToken.Operator("+"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("10"))
                        ),
                        MathToken.Variable("b")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "3 × 2 = 6, 5 × 2 = 10"
                ),
                // Step 5: Combine fractions
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(
                                MathToken.LeftBracket,
                                MathToken.Plain("6"),
                                MathToken.Operator("+"),
                                MathToken.Plain("1"),
                                MathToken.RightBracket
                            ),
                            denominator = listOf(MathToken.Plain("10"))
                        ),
                        MathToken.Variable("b")
                    ),
                    interactiveOptions = InteractiveOptions(
                        options = listOf("7", "5", "8", "6"),
                        correctAnswer = "7",
                        label = "6+1",
                        denominator = "10",
                        variable = "b"
                    ),
//                    explanation = "Add numerators: 6 + 1"
                ),
                // Step 6: Final answer
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("7")),
                            denominator = listOf(MathToken.Plain("10"))
                        ),
                        MathToken.Variable("b")
                    ),
//                    explanation = "Final simplified form"
                )
            )
        ),

        // Equation 5: 1/3m - 1/6m (Complete 6-step solution)
        Equation(
            title = "Solve: 1/3m - 1/6m",
            initialExpression = listOf(
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("1")),
                    denominator = listOf(MathToken.Plain("3"))
                ),
                MathToken.Variable("m"),
                MathToken.Operator("-"),
                MathToken.Fraction(
                    numerator = listOf(MathToken.Plain("1")),
                    denominator = listOf(MathToken.Plain("6"))
                ),
                MathToken.Variable("m")
            ),
            steps = listOf(

                // Step 2: Find LCD
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("3"))
                        ),
                        MathToken.Variable("m"),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("6"))
                        ),
                        MathToken.Variable("m")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "LCD of 3 and 6 is 6"
                ),
                // Step 3: Convert first fraction
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1"), MathToken.Operator("×"), MathToken.Plain("2")),
                            denominator = listOf(MathToken.Plain("3"), MathToken.Operator("×"), MathToken.Plain("2"))
                        ),
                        MathToken.Variable("m"),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("6"))
                        ),
                        MathToken.Variable("m")
                    ),
                    dotIndex = 0,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "Multiply 1/3 by 2/2"
                ),
                // Step 4: Simplify first fraction
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("2")),
                            denominator = listOf(MathToken.Plain("6"))
                        ),
                        MathToken.Variable("m"),
                        MathToken.Operator("-"),
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("6"))
                        ),
                        MathToken.Variable("m")
                    ),
                    dotIndex = 2,
                    dotPosition = DotPosition.ABOVE,
//                    explanation = "1 × 2 = 2, 3 × 2 = 6"
                ),
                // Step 5: Combine fractions
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(
                                MathToken.LeftBracket,
                                MathToken.Plain("2"),
                                MathToken.Operator("-"),
                                MathToken.Plain("1"),
                                MathToken.RightBracket
                            ),
                            denominator = listOf(MathToken.Plain("6"))
                        ),
                        MathToken.Variable("m")
                    ),
                    interactiveOptions = InteractiveOptions(
                        options = listOf("1", "3", "2", "0"),
                        correctAnswer = "1",
                        label = "2-1",
                        denominator = "6",
                        variable = "m"
                    ),
//                    explanation = "Subtract numerators: 2 - 1"
                ),
                // Step 6: Final answer
                EquationStep(
                    tokens = listOf(
                        MathToken.Fraction(
                            numerator = listOf(MathToken.Plain("1")),
                            denominator = listOf(MathToken.Plain("6"))
                        ),
                        MathToken.Variable("m")
                    ),
//                    explanation = "Final simplified form"
                )
            )
        ),


    )
}

// Main composable with equation selection
@Composable
fun MultiEquationSolver() {
    var selectedEquationIndex by remember { mutableStateOf(0) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Equation selector
        Text(
            "Select Equation:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            itemsIndexed(EquationRepository.equations) { index, equation ->
                Card(
                    modifier = Modifier
                        .clickable { selectedEquationIndex = index }
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedEquationIndex == index)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        else
                            MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        "Equation ${index + 1}",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = if (selectedEquationIndex == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        // Current equation solver
        XygosDotGuidedEquationSolver(
            equation = EquationRepository.equations[selectedEquationIndex],
            key = selectedEquationIndex // Reset state when equation changes
        )
    }
}

@Composable
fun XygosDotGuidedEquationSolver(
    equation: Equation,
    key: Any = Unit,
) {
    var currentStep by remember(key) { mutableStateOf(0) }
    val visibleSteps = remember(key) { mutableStateListOf(0) }

    // Animate new step in
    LaunchedEffect(currentStep) {
        if (!visibleSteps.contains(currentStep)) {
            delay(200)
            visibleSteps.add(currentStep)
        }
    }

    Column(
        Modifier.fillMaxWidth()
    ) {
        // Display equation title with initial expression
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                "${equation.title.substringBefore(":")}:",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.width(8.dp))
            MathRowWithDot(
                tokens = equation.initialExpression,
                dotIndex = -1,
                dotPosition = DotPosition.ABOVE
            )
        }

        visibleSteps.forEach { stepIdx ->
            val step = equation.steps[stepIdx]
            val showDot = stepIdx == currentStep && step.dotIndex != -1
            val dotIndex = if (showDot) step.dotIndex else -1

            AnimatedVisibility(
                visible = stepIdx in visibleSteps && step.interactiveOptions == null,
                enter = slideInVertically(
                    animationSpec = tween(200),
                    initialOffsetY = { it / 2 }
                ) + fadeIn(animationSpec = tween(200)),
                exit = fadeOut(animationSpec = tween(300)),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Column {
                    MathRowWithDot(
                        tokens = step.tokens,
                        dotIndex = dotIndex,
                        dotPosition = step.dotPosition,
                        onTap = { idx ->
                            if (idx == step.dotIndex && step.dotPosition == DotPosition.ABOVE && currentStep == stepIdx && currentStep < equation.steps.size - 1) {
                                currentStep = stepIdx + 1
                            }
                        },
                        onDrag = { idx, direction ->
                            if (idx == step.dotIndex && currentStep == stepIdx && currentStep < equation.steps.size - 1) {
                                val match = when (step.dotPosition) {
                                    DotPosition.BELOW -> direction == "down"
                                    DotPosition.LEFT -> direction == "left"
                                    DotPosition.RIGHT -> direction == "right"
                                    DotPosition.ABOVE -> direction == "up"
                                }
                                if (match) currentStep = stepIdx + 1
                            }
                        }
                    )

                    if (stepIdx < currentStep) {
                        Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Interactive options for current step
        val currentStepData =
            if (currentStep < equation.steps.size) equation.steps[currentStep] else null
        currentStepData?.interactiveOptions?.let { options ->
            val coroutineScope = rememberCoroutineScope()
            var selectedHint by remember(key, currentStep) { mutableStateOf<Int?>(null) }
            var showFeedback by remember(key, currentStep) { mutableStateOf(false) }

            FractionWithBoxedOptionsAndLabelNumerator(
                options = options.options,
                selectedIndex = selectedHint,
                onSelect = { idx ->
                    selectedHint = idx
                    showFeedback = true
                    coroutineScope.launch {
                        delay(100)
                        if (options.options[idx] == options.correctAnswer) {
                            if (currentStep < equation.steps.size - 1) {
                                currentStep += 1
                            }
                        }
                        delay(300) // show red box briefly
                        selectedHint = null
                        showFeedback = false
                    }
                },
                label = options.label,
                denominator = options.denominator,
                variable = options.variable,
                correctAnswer = options.correctAnswer,
                showFeedback = showFeedback
            )
        }

        // Reset button
        if (currentStep >= equation.steps.size - 1) {
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    currentStep = 0
                    visibleSteps.clear()
                    visibleSteps.add(0)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Reset")
            }
        }
    }
}

// Keep all the existing composables (MathTokenView, FractionView, MathRowWithDot, etc.)
@Composable
fun FractionWithBoxedOptionsAndLabelNumerator(
    options: List<String>,
    selectedIndex: Int?,
    onSelect: (Int) -> Unit,
    label: String,
    denominator: String,
    variable: String,
    correctAnswer: String? = null, // optional override for clarity
    showFeedback: Boolean = true   // default true
) {
    val showFractionLine = denominator.isNotBlank()

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        options.forEachIndexed { idx, opt ->
                            val borderColor = when {
                                selectedIndex == idx && showFeedback -> {
                                    if (opt == correctAnswer) Color(0xFF4CAF50) else Color.Red
                                }
                                else -> Color.Gray
                            }

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .border(
                                        2.dp,
                                        borderColor,
                                        RoundedCornerShape(6.dp)
                                    )
                                    .background(Color.Transparent, RoundedCornerShape(6.dp))
                                    .clickable { onSelect(idx) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    opt,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }

                    Text(
                        label,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            if (showFractionLine) {
                Spacer(Modifier.height(5.dp))
                Box(
                    Modifier
                        .height(2.dp)
                        .width(120.dp)
                        .background(Color.Black)
                )
                Text(
                    text = denominator,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(120.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.width(8.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(Modifier.height(49.dp))
            Text(
                variable,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

//@Composable
//fun MathTokenView(token: Any) {
//    when (token) {
//        is MathToken.Fraction -> FractionView(token.numerator, token.denominator)
//        is MathToken.Variable -> Text(token.name, fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.Operator -> Text(
//            token.symbol,
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Medium
//        )
//
//        is MathToken.Plain -> Text(token.value, fontSize = 24.sp)
//        is MathToken.LeftBracket -> Text("(", fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.RightBracket -> Text(")", fontSize = 24.sp, fontWeight = FontWeight.Medium)
//    }
//}

@Composable
fun MathTokenView(token: Any) {
    when (token) {
        is MathToken.Fraction -> FractionView(token.numerator, token.denominator)
        is MathToken.Variable -> Text(token.name, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.Operator -> Text(token.symbol, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.Plain -> Text(token.value, fontSize = 24.sp)
        is MathToken.LeftBracket -> Text("(", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.RightBracket -> Text(")", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.LeftSquareBracket -> Text("[", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.RightSquareBracket -> Text("]", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.LeftBrace -> Text("{", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        is MathToken.RightBrace -> Text("}", fontSize = 24.sp, fontWeight = FontWeight.Medium)

        is MathToken.TrigFunction -> Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${token.name}(", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            token.argument.forEach { MathTokenView(it) }
            Text(")", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
//@Composable
//fun MathTokenView(token: Any) {
//    when (token) {
//        is MathToken.Fraction -> FractionView(token.numerator, token.denominator)
//        is MathToken.Variable -> Text(token.name, fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.Operator -> Text(token.symbol, fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.Plain -> Text(token.value, fontSize = 24.sp)
//        is MathToken.LeftBracket -> Text("(", fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        is MathToken.RightBracket -> Text(")", fontSize = 24.sp, fontWeight = FontWeight.Medium)
//        // ✅ Render Trigonometric Function
////        is MathToken.TrigFunction -> Row(verticalAlignment = Alignment.CenterVertically) {
////            Text("${token.name}(", fontSize = 24.sp, fontWeight = FontWeight.Bold)
////            token.argument.forEach { MathTokenView(it) }
////            Text(")", fontSize = 24.sp, fontWeight = FontWeight.Bold)
////        }
//
//        is MathToken.TrigFunction -> {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(token.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//                Text("(", fontSize = 24.sp)
//                token.argument.forEach { MathTokenView(it) }
//                Text(")", fontSize = 24.sp)
//            }
//        }
//    }
//}

@Composable
fun FractionView(
    numerator: List<MathToken>,
    denominator: List<MathToken>,
    dotIndex: Int? = null,
    dotPosition: DotPosition? = null,
    onTap: ((Int) -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Row {
            numerator.forEachIndexed { idx, token ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .then(
                            if (onTap != null && dotIndex == idx && dotPosition == DotPosition.ABOVE) {
                                Modifier.clickable { onTap(idx) }
                            } else Modifier
                        )
                ) {
                    if (dotIndex == idx && dotPosition == DotPosition.ABOVE) {
                        FlashingBlueDot()
                        MathTokenView(token)
                    } else {
                        MathTokenView(token)
                    }
                }
            }
        }
        Box(
            Modifier
                .height(2.dp)
                .width(24.dp)
                .background(Color.Black)
        )
        Row {
            denominator.forEach { token ->
                MathTokenView(token)
            }
        }
    }
}

@Composable
fun MathRowWithDot(
    tokens: List<Any>,
    dotIndex: Int,
    dotPosition: DotPosition,
    onTap: ((Int) -> Unit)? = null,
    onDrag: ((Int, String) -> Unit)? = null, // String: "left", "right", "down", "up"
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        tokens.forEachIndexed { idx, token ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .then(
                        when {
                            onTap != null && dotIndex == idx && dotPosition == DotPosition.ABOVE ->
                                Modifier.clickable { onTap(idx) }
                            onDrag != null && dotIndex == idx && dotPosition != DotPosition.ABOVE ->
                                Modifier.pointerInput(Unit) {
                                    detectDragGestures { _, dragAmount ->
                                        when {
                                            dotPosition == DotPosition.BELOW && dragAmount.y > 30f -> onDrag(idx, "down")
                                            dotPosition == DotPosition.LEFT && dragAmount.x < -30f -> onDrag(idx, "left")
                                            dotPosition == DotPosition.RIGHT && dragAmount.x > 30f -> onDrag(idx, "right")
                                            dotPosition == DotPosition.ABOVE && dragAmount.y < -30f -> onDrag(idx, "up")
                                        }
                                    }
                                }
                            else -> Modifier
                        }
                    )
            ) {
                if (dotIndex == idx) {
                    when (dotPosition) {
                        DotPosition.ABOVE -> {
                            FlashingBlueDot()
                            MathTokenView(token)
                        }
                        DotPosition.BELOW -> {
                            MathTokenView(token)
                            FlashingBlueDot()
                        }
                        DotPosition.LEFT -> {
                            Row {
                                FlashingBlueDot()
                                MathTokenView(token)
                            }
                        }
                        DotPosition.RIGHT -> {
                            Row {
                                MathTokenView(token)
                                FlashingBlueDot()
                            }
                        }
                    }
                } else {
                    MathTokenView(token)
                }
            }
        }
    }
}

@Composable
fun FlashingBlueDot(
    modifier: Modifier = Modifier,
    size: Dp = 14.dp,
) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            scale.snapTo(1f)
            delay(500)
            scale.animateTo(
                targetValue = 1.5f,
                animationSpec = tween(durationMillis = 200)
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 200)
            )
        }
    }

    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            }
            .background(Color(0xFF1976D2), CircleShape)
    )
}
