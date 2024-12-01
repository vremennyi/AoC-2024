
import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val l = PriorityQueue<Int>()
        val r = PriorityQueue<Int>()
        input.forEach { s ->
            val (left, right) = s.split(Regex("\\s+"), 2).map(String::toInt)
            l.add(left)
            r.add(right)
        }
        var sum = 0
        while (l.isNotEmpty() && r.isNotEmpty()) {
            sum += abs(l.remove() - r.remove())
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val l = arrayListOf(input.size)
        val counts = mutableMapOf<Int, Int>()
        input.forEach { s ->
            val (left, right) = s.split(Regex("\\s+"), 2).map(String::toInt)
            l.add(left)
            counts[right] = (counts[right] ?: 0) + 1
        }
        return l.sumOf { num -> num * (counts[num] ?: 0) }
    }

    val testInput = readInput("input/Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("input/Day01")
    part1(input).println()
    part2(input).println()
}
