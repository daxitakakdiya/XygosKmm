package org.example.xygos.ui.derivativedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.xygos.theme.AppColors

@Composable
fun DerivativeMathExpression(
    highlightInner: Boolean = false,
    showHint: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Fraction d / dθ
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            Text("d", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(22.dp)
                    .background(AppColors.White)
            )
            Text("dθ", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
        }

        // The rest: [ sin(2θ) ]
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("[ sin(", fontSize = 24.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
            if (highlightInner) {
                Box(
                    modifier = Modifier
                        .background(
                            (if (showHint) AppColors.Accent else Color.Transparent),
                            shape = CircleShape
                        )
                        .padding(horizontal = 2.dp)
                ) {
                    Text(
                        "2θ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.White
                    )
                }
            } else {
                Text("2θ", fontSize = 24.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
            }
            Text(") ]", fontSize = 24.sp, fontWeight = FontWeight.Medium, color = AppColors.White)
        }
    }
}