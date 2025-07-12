package org.example.xygos.newmaths

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import platform.WebKit.WKWebView
import platform.darwin.NSObject

actual class MathRenderer actual constructor(
    private val textColor: String,
    private val bgColor: String
) : NSObject() {
    private var currentEquation: String = ""

    @Composable
    actual fun RenderEquation(equation: String) {
        currentEquation = equation
        UIKitView(
            factory = {
                WKWebView().apply {
                    loadHTMLString(createHtml(), null)
                }
            },
            update = { view in
                    (view as WKWebView).loadHTMLString(createHtml(), null)
            }
        )
    }

    actual fun updateEquation(equation: String) {
        currentEquation = equation
    }

    private fun createHtml(): String {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.css">
            <style>
                body { 
                    margin: 0; 
                    padding: 8px;
                    background-color: $bgColor;
                }
                #math { 
                    color: $textColor;
                }
            </style>
        </head>
        <body>
            <div id="math">$currentEquation</div>
            <script src="https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.js"></script>
            <script>
                katex.render(
                    document.getElementById('math').textContent,
                    document.getElementById('math'),
                    { throwOnError: false }
                );
            </script>
        </body>
        </html>
        """
    }
}