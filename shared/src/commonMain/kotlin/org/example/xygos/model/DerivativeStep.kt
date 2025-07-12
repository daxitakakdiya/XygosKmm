package org.example.xygos.model

//enum class DerivativeStepType {
//    Start,
//    HighlightInner,
//    TrigWheel,
//    ChainRule,
//    ConstantOut,
//    Final,
//    Summary
//}

//data class DerivativeStep(
//    val type: DerivativeStepType,
//    val hint: String,
//    val actionable: Boolean = false,
//)
// Add new step type
enum class DerivativeStepType {
    Start,
    HighlightInner,
    TrigWheel,
    ChainRule,
    ConstantOut,
    ExpandSum,
    ProductRule,
    DerivativeFirst,
    DerivativeSecond,
    DerivativeEachTerm,
    Combine,
    Final,
    Summary,
}
data class DerivativeStep(
    val type: DerivativeStepType,
    val hint: String,
    val actionable: Boolean,
    val expression: String? = null,      // For MathExpression
    val correctAnswer: String? = null    // For TrigWheel, etc.
)