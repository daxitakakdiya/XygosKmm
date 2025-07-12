package org.example.xygos

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.example.xygos.ui.EquationApp
import org.example.xygos.ui.derivativedemo.XygosDerivativeStepDemo

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
//        App()
//        EquationApp()
        XygosDerivativeStepDemo()
    }
}