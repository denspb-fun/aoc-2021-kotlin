fun main() {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    val day = "01"

    val testInput = readInput("Day${day}_test")
//    check(part1(testInput) == 1)
//    check(part2(testInput) == 2)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
