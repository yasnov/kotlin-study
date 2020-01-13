package math

interface Expr

class Num(val value: Int) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num ${e.value}")
            e.value
        }
        is Sum -> {
            val left = eval(e.left)
            val right = eval(e.right)
            println("sum ${left} + ${right}")
            left + right
        }
        else -> {
            throw IllegalArgumentException("Unknown expression")
        }
    }