package org.example.xygos.model

data class EquationStep(
    val id: Int=0,
    val equationParts: List<String>,
    val hint: String,
    val interactionType: InteractionType,
    val expectedAnswer: String? = null,
    val options: List<String> = emptyList()
)


data class EquationStep2(
    val id: Int,
    val equationLatex: String,
    val hint: String,
    val interactionType: InteractionType,
    val options: List<String>? = null,
    val interactiveParts: List<String>? = null, // NEW
    val targetArea: String? = null // NEW
)

sealed class InteractionType {
    object Tap : InteractionType()
    object DragDrop : InteractionType()
    object None : InteractionType()
}

