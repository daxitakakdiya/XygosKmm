package org.example.xygos.model

//sealed class MathToken {
//    data class Fraction(val numerator: List<MathToken>, val denominator: List<MathToken>) : MathToken()
//    data class Variable(val name: String) : MathToken()
//    data class Operator(val symbol: String) : MathToken()
//    data class Plain(val value: String) : MathToken()
//    object LeftBracket : MathToken()
//    object RightBracket : MathToken()
//}
//
//enum class DotPosition { ABOVE, BELOW }

// Required enums and sealed classes (add these if not already defined)
enum class DotPosition {
    ABOVE, BELOW, LEFT, RIGHT
}
sealed class MathToken {
    object LeftBracket : MathToken()         // (
    object RightBracket : MathToken()        // )
    object LeftSquareBracket : MathToken()   // [
    object RightSquareBracket : MathToken()  // ]
    object LeftBrace : MathToken()           // {
    object RightBrace : MathToken()          // }

    data class Fraction(val numerator: List<MathToken>, val denominator: List<MathToken>) : MathToken()
    data class TrigFunction(val name: String, val argument: List<MathToken>) : MathToken()
    data class Operator(val symbol: String) : MathToken()
    data class Plain(val value: String) : MathToken()
    data class Variable(val name: String) : MathToken()
}