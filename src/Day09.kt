class Cell(val x : Int, val y: Int, val d: Int) {
}

fun main() {
    fun board(input: List<String>): List<List<Cell>> {
        val data = input.map { it.map { it.digitToInt() } }
        val result = ArrayList<List<Cell>>()
        for ((i, r) in data.withIndex()) {
            val row = ArrayList<Cell>()
            for ((j, v) in r.withIndex()) {
                row += Cell(i,j,v)
            }
            result += row
        }
        return result
    }

    fun getAt(board : List<List<Cell>>, i: Int, j:Int) : Cell? {
        return board.getOrNull(i)?.getOrNull(j)
    }

    fun neighbors(board : List<List<Cell>>, c: Cell): List<Cell> {
        return listOfNotNull(getAt(board, c.x + 1, c.y), getAt(board, c.x - 1, c.y), getAt(board, c.x, c.y + 1), getAt(board, c.x, c.y - 1))
    }

    fun part1(input: List<String>): Int {
        val b = board(input)
        var result = 0
        for (row in b) for (c in row) {
            if (c.d < neighbors(b, c).minOf { it.d }) {
                result += c.d + 1
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val b = board(input)
        val marked =  Array(b.size) { BooleanArray(b.first().size) }
        val basins = ArrayList<Int>()
        fun dfs(c : Cell): Int {
            if (marked[c.x][c.y])
                return 0
            if (c.d == 9)
                return 0
            marked[c.x][c.y] = true
            return 1 + neighbors(b, c).sumOf { dfs(it) }
        }
        for (row in b) for (c in row) {
            dfs(c).let {
                if (it > 0)
                    basins += it
            }
        }
        return basins.sortedDescending().take(3).fold(1 ) {acc, i -> acc* i }
    }

    val day = "09"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)
    println("Checks passed")

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
