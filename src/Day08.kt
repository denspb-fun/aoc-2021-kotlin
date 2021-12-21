val DIGITS = mapOf<String, Int>("ABCEFG" to 0, "CF" to 1, "ACDEG" to 2, "ACDFG" to 3, "BCDF" to 4, "ABDFG" to 5, "ABDEFG" to 6, "ACF" to 7, "ABCDEFG" to 8, "ABCDFG" to 9)

fun main() {
    fun p1CCount(digits:List<String>, values:List<String>) : Int {
        val lenMap = values.groupBy { it.length }
        val answer = listOf<List<String>?>(lenMap[2], lenMap[3], lenMap[4], lenMap[7])
        return answer.filterNotNull().sumOf { it.size }
    }

    fun p2CCount(digits:List<String>, values:List<String>) : Int {
        val lenMap = digits.groupBy { it.length }
        val countMap = digits.joinToString(separator = "").toCharArray().groupBy{it}.mapValues{(_, v)-> v.size}
        val mapping = HashMap<Char, Char>()
        for ((d, size) in countMap.entries) {
            when (size) {
                4 -> mapping[d] = 'E'
                6 -> mapping[d] = 'B'
                9 -> mapping[d] = 'F'
            }
        }
        val one = lenMap.getOrDefault(2, emptyList()).getOrElse(0) { "" }
        val seven = lenMap.getOrDefault(3, emptyList()).getOrElse(0) { "" }
        val four = lenMap.getOrDefault(4, emptyList()).getOrElse(0) { "" }
        val eight = lenMap.getOrDefault(7, emptyList()).getOrElse(0) { "" }
        mapping[one.toCharArray().subtract(mapping.keys).first()] = 'C'
        mapping[seven.toCharArray().subtract(mapping.keys).first()] = 'A'
        mapping[four.toCharArray().subtract(mapping.keys).first()] = 'D'
        mapping[eight.toCharArray().subtract(mapping.keys).first()] = 'G'
        val newDigits = values.map{ it.toCharArray().map{mapping[it]}.filterNotNull().sorted().joinToString (separator="" )}
        return newDigits.mapNotNull{DIGITS[it]}.fold(0){result, d -> result * 10 + d }
    }


    fun part1(input: List<String>): Int {
        return input.map { it.split(" | ")}.sumOf{ p1CCount(it[0].split(" "), it[1].split(" ")) }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(" | ")}.sumOf{ p2CCount(it[0].split(" "), it[1].split(" ")) }
    }

    val day = "08"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)
    println("Checks passed")

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
