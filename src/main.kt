import math.*

const val TEST = "ADDAD"

class Person(val name: String, var isMarried: Boolean) {
    val isStudent: Boolean
        get() = name.length <= 4
}

enum class WorkStatus {
    SLEEP, EAT, WORK, STUDY
}

fun getMnemonic(status: WorkStatus) =
    when (status) {
        WorkStatus.SLEEP -> "Сплю"
        WorkStatus.EAT -> "Кушаю"
        else -> "Что-то там ещё"
    }


fun main(args: Array<String>) {
    println(TEST)
    val person = Person("Mark", false)
    println(person.name)
    println(person.isMarried)
    println(person.isStudent)

    println(getMnemonic(WorkStatus.SLEEP))


    val characters: CharSequence = "abc"
    for (element in characters) {
        println(element)
    }
    for (c in 'a'..'f'){
        print(c)
    }

    println(max(1, 2))
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    if (args.isNotEmpty()) {
        println("You ${args[0]}")
    }
    println("Oh. ${if (args.isEmpty()) "I'm sorry" else "Hi " + args[0]}")
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

