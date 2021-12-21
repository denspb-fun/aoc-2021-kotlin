fun main() {
    fun cycle(prev: LongArray) : LongArray {
        val result = LongArray(9)
        for (i in 0..7)
            result[i] = prev[i+1]
        result[8] = prev[0]
        result[6] += prev[0]
        return result
    }

    fun part1(input: List<String>): Long {
        val init = input.first().split(",").map {it.toInt()}
        var data = LongArray(9)
        for (fish in init)
            data[fish] ++
        for (day in 1..80) {
            data = cycle(data)
        }
        return data.sum()
    }

    fun part2(input: List<String>): Long {
        val init = input.first().split(",").map {it.toInt()}
        var data = LongArray(9)
        for (fish in init)
            data[fish] ++
        for (day in 1..256) {
            data = cycle(data)
        }
        return data.sum()
    }

    val day = "06"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
