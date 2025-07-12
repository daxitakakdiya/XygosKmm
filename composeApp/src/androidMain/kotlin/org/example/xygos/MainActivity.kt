package org.example.xygos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.xygos.ui.derivativedemo.XygosDerivativeStepDemo
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
//                EquationApp()//multiple equations simply solved
//                MultiEquationSolver()// multiple equations solved like xygos
                XygosDerivativeStepDemo()// single sin cos...
//                MultiEquationDerivativeDemo()
//                MathSolverScreen()
//                XygosLLMUI()

            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    App()
//    EquationApp()
    XygosDerivativeStepDemo()
}
