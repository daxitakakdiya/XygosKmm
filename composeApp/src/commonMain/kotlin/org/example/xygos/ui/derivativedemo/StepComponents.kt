package org.example.xygos.ui.derivativedemo

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.xygos.theme.AppColors

@Composable
fun ChainRuleStep(showHint: Boolean)
{
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            // d/dθ
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "d",
                    fontSize = 16.sp,
                    color = if (showHint) AppColors.Accent else AppColors.White,
                    fontWeight = FontWeight.Bold
                )
                if (showHint) HintDot()
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(22.dp)
                    .background(AppColors.White)
            )
            Text(
                "dθ",
                fontSize = 16.sp,
                color = if (showHint) AppColors.Accent else AppColors.White,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            "[ cos(2θ) ]",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.White
        )
    }
}

@Composable
fun ConstantOutStep(showHint: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("cos(2θ) ", fontSize = 24.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            // d/dθ
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "d",
                    fontSize = 16.sp,
                    color = if (showHint) AppColors.Accent else AppColors.White,
                    fontWeight = FontWeight.Bold
                )
                if (showHint) HintDot()
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(22.dp)
                    .background(AppColors.White)
            )
            Text(
                "dθ",
                fontSize = 16.sp,
                color = if (showHint) AppColors.Accent else AppColors.White,
                fontWeight = FontWeight.Bold
            )
        }
        Text("2 [ θ ]", fontSize = 24.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
    }
}

@Composable
fun FinalStep() {
    Text("cos(2θ) 2", fontSize = 24.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
}

@Composable
fun SummaryStep() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.SummaryBackground, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("Initial Function:", color = AppColors.White, fontSize = 16.sp)
        Text("sin(2θ)", color = AppColors.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Derivative:", color = AppColors.Accent, fontSize = 16.sp)
        Text("cos(2θ) 2", color = AppColors.Accent, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { /* Next Problem */ },
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary)
        ) {
            Text("Next Problem", color = AppColors.White)
        }
    }
}

@Composable
fun HintDot() {
    val transition = rememberInfiniteTransition(label = "hintdot")
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    Box(
        modifier = Modifier
            .offset { IntOffset(0, -16) }
            .size(18.dp)
            .scale(scale)
            .background(AppColors.Warning, shape = CircleShape)
    )
}