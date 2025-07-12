package org.example.xygos.newmaths

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
actual fun MathViewold(formula: String, modifier: Modifier) {
    val latex = formula
        .replace("\\", "\\\\")  // escape backslashes
        .replace("\"", "\\\"") // escape quotes

//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            WebView(context).apply {
//                settings.javaScriptEnabled = true
//                loadDataWithBaseURL(
//                    null,
//                    """
//                        <html>
//                        <head>
//                            <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/katex@0.16.0/dist/katex.min.css\">
//                            <script src=\"https://cdn.jsdelivr.net/npm/katex@0.16.0/dist/katex.min.js\"></script>
//                        </head>
//                        <body style=\"margin:0;\">
//                            <div id=\"math\" style=\"font-size: 24px; padding: 8px;\"></div>
//                            <script>
//                                katex.render(\"\\( $latex \\)\", document.getElementById(\"math\"), { throwOnError: false });
//                            </script>
//                        </body>
//                        </html>
//                    """.trimIndent(),
//                    "text/html",
//                    "utf-8",
//                    null
//                )
//            }
//        }
//    )
}

actual class MathView actual constructor() {
    private var webView: WebView? = null  // Changed to nullable instead of lateinit
    private var clickListener: ((String) -> Unit)? = null

    @SuppressLint("SetJavaScriptEnabled")
    actual fun renderEquation(equation: String) {
        webView?.let { view ->
            val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
                <style>
                    body { margin: 0; padding: 0; }
                    .math-part { cursor: pointer; }
                </style>
            </head>
            <body>
                <div id="formula">\\($equation\\)</div>
                <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        setTimeout(function() {
                            Array.from(document.getElementsByClassName('mjx-chtml')).forEach(el => {
                                el.style.cursor = 'pointer';
                                el.addEventListener('click', function() {
                                    AndroidMathView.onPartClicked(el.textContent);
                                });
                            });
                        }, 500);
                    });
                </script>
            </body>
            </html>
            """

            view.loadDataWithBaseURL(
                null,
                html,
                "text/html",
                "UTF-8",
                null
            )
        }
    }

    actual fun setOnPartClickListener(listener: (String) -> Unit) {
        this.clickListener = listener
    }

    @JavascriptInterface
    fun onPartClicked(part: String) {
        clickListener?.invoke(part)
    }

    @Composable
    fun AndroidMathView() {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    addJavascriptInterface(this@MathView, "AndroidMathView")
                    webView = this  // Initialize here
                }
            },
            update = { view ->
                webView = view  // Ensure reference is maintained
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}