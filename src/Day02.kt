fun main() {
    fun part1(input: List<String>): Int {
        var shift = 0
        var depth = 0
        for (line in input) {
            val (cmd, param) = line.split(" ")
            when (cmd) {
                "forward" -> shift += param.toInt()
                "down" -> depth += param.toInt()
                "up" -> depth -= param.toInt()
            }
        }
        return shift*depth
    }

    fun part2(input: List<String>): Int {
        var shift = 0
        var depth = 0
        var aim = 0
        for (line in input) {
            val (cmd, param) = line.split(" ")
            when (cmd) {
                "forward" -> {
                    shift += param.toInt()
                    depth += aim * param.toInt()
                }
                "down" -> {
                    aim += param.toInt()
                }
                "up" -> {
                    aim -= param.toInt()
                }
            }
        }
        return shift*depth
    }

    val day = "02"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
