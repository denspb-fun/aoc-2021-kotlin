
fun main() {
    val closingCost = mapOf<Char, Int>(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    fun score1(s:String) : Int {
        val stack = ArrayDeque<Char>()
        for (c in s.toCharArray()) {
            when (c) {
                stack.lastOrNull() -> stack.removeLast()
                '[' -> stack.addLast(']')
                '(' -> stack.addLast(')')
                '{' -> stack.addLast('}')
                '<' -> stack.addLast('>')
                ')' -> return 3
                ']' -> return 57
                '}' -> return 1197
                '>' -> return 25137
            }
        }
        return 0
    }

    fun score2(s:String) : Long {
        val stack = ArrayDeque<Char>()
        for (c in s.toCharArray()) {
            when (c) {
                stack.lastOrNull() -> stack.removeLast()
                '[' -> stack.addLast(']')
                '(' -> stack.addLast(')')
                '{' -> stack.addLast('}')
                '<' -> stack.addLast('>')
                ')', ']','}','>' -> return 0
            }
        }
        return stack.foldRight(0L) {c, acc -> acc * 5 + closingCost.getOrDefault(c, 0)}
    }

    fun part1(input: List<String>): Int {
        return input.map { score1(it) }.sum()
    }

    fun part2(input: List<String>): Long {
        val scores = input.map { score2(it)}.filter { it > 0 }.sorted()
        return scores[scores.size / 2]
    }

    val day = "10"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)
    println("Checks passed")

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
