import java.lang.Math.max
import java.lang.Math.min

class Board11(input:List<String>) {
    val f = input.map { it.map { it.digitToInt() }.toMutableList() }
    val n = f.size
    val m = f.first().size

    fun neighbors(x : Int, y: Int) : Sequence<Pair<Int, Int>> {
        return sequence<Pair<Int, Int>> {
            for (i in max(x - 1, 0)..min(x+1, n-1))
                for (j in max(y - 1, 0)..min(y+1, m-1))
                    if (i != x || j != y)
                        yield(Pair(i, j))
        }
    }

    operator fun set(p: Pair<Int,Int>, v:Int) {
        f[p.first][p.second] = v
    }

    override fun toString() : String{
        return f.map{it.joinToString(separator = ".")}.joinToString(separator = "\n")
    }
}

fun main() {

    fun tick(b:Board11): Int {
        val queue = ArrayList<Pair<Int,Int>>()
        fun charge(p : Pair<Int,Int>) {
            b.f[p.first][p.second]++
            if (b.f[p.first][p.second] == 10) queue += p
        }
        for (i in 0 until b.n) for (j in 0 until b.m)
            charge(Pair(i, j))
        var queueHead = 0
        while (queueHead < queue.size) {
            val flashed = queue[queueHead]
            queueHead++
            for (squid in b.neighbors(flashed.first, flashed.second))
                charge(squid)
        }
        for (squid in queue)
            b[squid] = 0
        return queue.size
    }

    fun part1(input: List<String>): Int {
        val b = Board11(input)
        var result = 0
        repeat(100) {
            result += tick(b)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val b = Board11(input)
        var step = 0
        while (true) {
            step++
           if (tick(b) == 100)
               return step
        }
    }

    val day = "11"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)
    println("Checks passed")

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
