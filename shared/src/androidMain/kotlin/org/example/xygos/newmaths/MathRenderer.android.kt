package org.example.xygos.newmaths

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

actual class MathRenderer actual constructor(private val textColor: String,
                                             private val bgColor: String){
    private var currentEquation: String = ""

    @Composable
    actual fun RenderEquation(equation: String) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    loadDataWithBaseURL(
                        "https://katex.org/",
                        """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.css">
                            <style>body {margin:0;padding:8px;}</style>
                        </head>
                        <body>
                            <div id="math">$equation</div>
                            <script src="https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.js"></script>
                            <script>
                                katex.render(
                                    document.getElementById('math').textContent,
                                    document.getElementById('math'),
                                    { throwOnError: false, displayMode: true }
                                );
                            </script>
                        </body>
                        </html>
                        """,
                        "text/html",
                        "UTF-8",
                        null
                    )
                }
            },
            update = { webView ->
                webView.loadDataWithBaseURL("https://katex.org/",
                    """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.css">
                            <style>body {margin:0;padding:8px;}</style>
                        </head>
                        <body>
                            <div id="math">$equation</div>
                            <script src="https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.js"></script>
                            <script>
                                katex.render(
                                    document.getElementById('math').textContent,
                                    document.getElementById('math'),
                                    { throwOnError: false, displayMode: true }
                                );
                            </script>
                        </body>
                        </html>
                        """,
                    "text/html",
                    "UTF-8",
                    null
                ) // Same as above
            }
        )
        }

    actual fun updateEquation(equation: String) {
    }
}
