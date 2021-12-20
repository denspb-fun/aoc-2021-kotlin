fun main() {
    fun part1(input: List<String>): Int {
        val len = input[0].length
        val ones = IntArray(len)
        val total = input.size
        for (line in input) {
            for (i in line.indices) {
                when(line[i]) {
                    '1' -> ones[i]++
                }
            }
        }
        val gamma = CharArray(len) { '0' }
        val epsilon = CharArray(len) { '0' }
        for (i in 0 until len) {
            if (ones[i] > total / 2 ) gamma[i] = '1' else epsilon[i] = '1'
        }
        val g = gamma.concatToString().toInt(2)
        val e = epsilon.concatToString().toInt(2)
        return g*e
    }

    fun find(input: List<String>, use_frequent: Boolean):Int {
        var set = input
        var pos = 0
        val len = input[0].length
        while (set.size > 1 && pos < len) {
            val bits = set.filter{line -> line[pos] == '1'}.size
            val keep_value = if (bits*2 >= set.size) {
                if (use_frequent) '1' else '0'
            } else {
                if (use_frequent) '0' else '1'
            }
            set = set.filter{line -> line[pos] == keep_value}
            pos ++
        }
        return set.first().toInt(2)
    }

    fun part2(input: List<String>): Int {
        return find (input, true) * find(input, false)
    }

    val day = "03"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
