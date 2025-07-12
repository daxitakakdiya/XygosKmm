package org.example.xygos.newmaths

interface MathRenderer1 {
    fun render(formula: String)
}

expect class MathRendererFactory {
    fun createRenderer(): MathRenderer1
}

