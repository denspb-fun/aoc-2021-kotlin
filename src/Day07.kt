import java.lang.Math.abs
import java.lang.Math.min

fun main() {
    fun part1(input: List<String>): Int {
        val crabs = input.first().split(",").map {it.toInt()}.sorted()
        val m = crabs[crabs.size / 2]
        return crabs.sumOf { abs(m - it) }
    }

    fun part2(input: List<String>): Long {
        val crabs = input.first().split(",").map {it.toInt()}
        val m = crabs.sum() / crabs.size
        return min(crabs.map{abs(m - it + 1)}.sumOf{ (it.toLong() * (it + 1) / 2)},
                   crabs.map{abs(m - it)}.sumOf{ (it.toLong() * (it + 1) / 2)})

    }

    val day = "07"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168L)
    println("Checks passed")

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
