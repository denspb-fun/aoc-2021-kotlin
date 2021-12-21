import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Math.abs

data class Point(val x: Int, val y: Int)


fun between(one: Int, value: Int, another: Int): Boolean {
    return min(one, another) <= value && value <= max(one, another)
}

class Line(val p1: Point, val p2: Point) {
    fun flat(): Boolean {
        return p1.x == p2.x || p1.y == p2.y
    }

    fun diagonal(): Boolean {
        return abs(p1.x - p2.x) == abs(p1.y - p2.y)
    }

    fun contains(p: Point): Boolean {
        return between(p1.x, p.x, p2.x) && between(p1.y, p.y, p2.y) &&
                ((p1.x - p.x) * (p1.y - p2.y) == (p1.y - p.y) * (p1.x - p2.x))
    }

    override fun toString(): String {
        return "$p1 -> $p2"
    }
}

fun main() {
    fun parse(input: List<String>): List<Line> {
        val format = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
        return input.mapNotNull { format.matchEntire(it)?.destructured }.map { (x1, y1, x2, y2) -> Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt())) }
    }

    fun part1(input: List<String>): Int {
        val lines = parse(input).filter { it.flat() }
        val maxX = max(lines.maxOf { it.p1.x }, lines.maxOf { it.p2.x })
        val maxY = max(lines.maxOf { it.p1.y }, lines.maxOf { it.p2.y })
        val minX = min(lines.minOf { it.p1.x }, lines.minOf { it.p2.x })
        val minY = min(lines.minOf { it.p1.y }, lines.minOf { it.p2.y })
        var result = 0
        for (x in minX..maxX) for (y in minY..maxY) {
            val p = Point(x, y)
            if (2 <= lines.count { it.contains(p) })
                result++
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val lines = parse(input).filter { it.flat() || it.diagonal() }
        val maxX = max(lines.maxOf { it.p1.x }, lines.maxOf { it.p2.x })
        val maxY = max(lines.maxOf { it.p1.y }, lines.maxOf { it.p2.y })
        val minX = min(lines.minOf { it.p1.x }, lines.minOf { it.p2.x })
        val minY = min(lines.minOf { it.p1.y }, lines.minOf { it.p2.y })
        var result = 0
        for (x in minX..maxX) for (y in minY..maxY) {
            val p = Point(x, y)
            if (2 <= lines.count { it.contains(p) })
                result++
        }

        return result
    }


    val day = "05"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}
