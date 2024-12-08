import kotlin.math.abs

enum class Direction {
    ASC, DESC
}

fun main() {

    fun isSafe(report: List<Int>): Boolean {
        if (report.size == 1) {
            return false
        }
        val dir = if (report[0] < report[1]) Direction.ASC else Direction.DESC
        var prev = report[0]
        report.drop(1).forEach {
            if (it == prev
                || (dir == Direction.ASC && it < prev)
                || (dir == Direction.DESC && it > prev)
                || abs(it - prev) > 3
            ) {
                return false
            }
            prev = it
        }
        return true
    }

    fun isSafeF(report: List<Int>): Boolean {
        val diff = report.zipWithNext { l, r -> l - r }
        return diff.all { it in -3..-1 } || diff.all { it in 1..3 }
    }

    fun isSafeDampened(report: List<Int>): Boolean {
        return report.indices.any {
            isSafe(report.toMutableList().apply { removeAt(it) })
//            isSafe(report.filterIndexed { index, _ -> index != it })
        }
    }

    fun part1(input: List<List<Int>>): Int {
        return input.count { isSafe(it) }
    }

    fun part2(input: List<List<Int>>): Int {
        return input.count { isSafeDampened(it) }
    }

    fun parse(input: List<String>): List<List<Int>> = input.map { it.split(" ").map(String::toInt) }

    val testInput = parse(readInput("input/Day02_test"))
    check(part1(testInput) == 2)
//    check(part2(testInput) == 4)

    val input = parse(readInput("input/Day02"))
    part1(input).println()
    part2(input).println()
}
