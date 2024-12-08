enum class ScanDirection(val dx: Int, val dy: Int) {
    N(-1, 0),
    NE(-1, 1),
    E(0, 1),
    SE(1, 1),
    S(1, 0),
    SW(1, -1),
    W(0, -1),
    NW(-1, -1);

    companion object {
        fun diagonals() = arrayListOf(NE, SE, SW, NW)
    }
}

fun main() {

    fun scan(grid: Array<CharArray>, row: Int, col: Int, direction: ScanDirection, word: String): Boolean {
        word.forEachIndexed { index, c ->
            val i = row + index * direction.dx
            val j = col + index * direction.dy
            if (i !in grid.indices || j !in grid[row].indices) return false
            val test = grid[i][j]
            if (c != test)
                return false
        }
        return true
    }

    fun part1(grid: Array<CharArray>): Int {
        var total = 0
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] != 'X') {
                    continue
                }
                total += ScanDirection.entries.count { scan(grid, row, col, it, "XMAS") }
            }
        }
        return total
    }

    fun part2(grid: Array<CharArray>): Int {
        var total = 0
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] != 'A') {
                    continue
                }
                if (ScanDirection.diagonals().count {
                    scan(grid, row - it.dx, col - it.dy, it, "MAS")
                } == 2)
                    total++
            }
        }
        return total
    }

    fun parseInput(input: List<String>): Array<CharArray> = input.map { it.toCharArray() }.toTypedArray()

    val testInput = parseInput(readInput("input/Day04_test"))
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = parseInput(readInput("input/Day04"))
    part1(input).println()
    part2(input).println()
}
