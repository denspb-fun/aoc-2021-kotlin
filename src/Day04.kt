class Board(lines: List<String>) {
    val field = lines.map { line -> line.trim().split(" ").filter { it != "" }.map { it.toInt() } }
    val marked = Array(5) { BooleanArray(5) }

    fun markAndCheck(num:Int): Boolean {
        var location:Pair<Int,Int>? = null
        outer@for (i in 0..4)
            for (j in 0..4) {
                if (field[i][j] == num && !marked[i][j]) {
                    marked[i][j] = true
                    location = Pair(i, j)
                    break@outer
                }
            }
        location?.let {
            val (ii, jj) = it
            return marked[ii].all { it } || marked.all{it [jj]}
        }
        return false
    }

    fun score(): Int {
        var result = 0
        for (i in 0..4)
            for (j in 0..4) {
                if (!marked[i][j])
                    result += field[i][j]
            }
        return result
    }
}

fun main() {
    fun parse(input: List<String>): Pair<List<Int>, List<Board>> {
        val moves = input.first().split(",").map { it.toInt()}
        val boards = input.drop(2).chunked(6).map{ it.take(5)}.map{Board(it)}
        return Pair(moves, boards)
    }

    fun part1(input: List<String>): Int {
        val (moves, boards) = parse(input)
        for(move in moves) {
            for (board in boards) {
                if (board.markAndCheck(move)) {
                    return board.score() * move
                }
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val (moves, boards) = parse(input)
        var lastScore = 0
        val wonBoard = BooleanArray(boards.size)
        for(move in moves) {
            for ((i, board) in boards.withIndex()) {
                if (!wonBoard[i] && board.markAndCheck(move)) {
                    wonBoard[i] = true
                    lastScore = board.score() * move
                }
            }
        }
        return lastScore
    }

    val day = "04"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
