fun main() {

    fun graph(input: List<String>): Map<String,Set<String>> {
        val result = HashMap<String, HashSet<String>>()
        for (line in input) {
            val (v1, v2) = line.split('-')
            result.getOrPut(v1){HashSet()}.add(v2)
            result.getOrPut(v2){HashSet()}.add(v1)
        }
        return result
    }

    fun step1(g: Map<String,Set<String>>, node:String, visited:MutableSet<String>): Int {
        if (node == "end")
            return 1
        var result = 0
        for (neighbor in g[node]!!) {
            if (neighbor == "start")
                continue
            val small = neighbor.first().isLowerCase()
            if (small && neighbor in visited)
                continue
            if (small)
                visited += neighbor
            result += step1(g, neighbor, visited)
            if (small)
                visited -= neighbor
        }
        return result
    }

    fun step2(g: Map<String,Set<String>>, node:String, visited:MutableSet<String>, canRepeat: Boolean): Int {
        if (node == "end")
            return 1
        var result = 0
        for (neighbor in g[node]!!) {
            if (neighbor == "start")
                continue
            val small = neighbor.first().isLowerCase()
            var newRepeat = canRepeat
            var added = false
            if (small) {
                if (neighbor in visited) {
                    if (!canRepeat)
                        continue
                    newRepeat = false
                } else {
                    added = true
                    visited += neighbor
                }

            }
            result += step2(g, neighbor, visited, newRepeat)
            if (added)
                visited -= neighbor
        }
        return result
    }

    fun part1(input: List<String>): Int {
        return step1(graph(input), "start", HashSet())
    }

    fun part2(input: List<String>): Int {
        return step2(graph(input), "start", HashSet(), true)
    }

    val day = "12"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 226)
    check(part2(testInput) == 3509)
    println("Checks passed")

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
