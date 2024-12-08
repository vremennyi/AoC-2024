fun main() {

    fun part1(input: String): Int {
        val pattern = """mul\((\d+),(\d+)\)""".toRegex()
        return pattern.findAll(input)
            .sumOf {
                it.groupValues
                    .drop(1)
                    .map(String::toInt)
                    .reduce(Int::times)
            }
    }

    fun part2(input: String): Int {
        val pattern = """mul\((\d+),(\d+)\)|do\(\)|don't\(\)""".toRegex()
        var sum = 0

        var skip = false
        pattern.findAll(input)
            .forEach {
                when (it.value) {
                    "don't()" -> skip = true
                    "do()" -> skip = false
                    else -> {
                        sum += if (skip) 0 else it.groupValues[1].toInt() * it.groupValues[2].toInt()
                    }
                }
            }
        return sum
    }

    check(part1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") == 161)
    check(part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") == 48)

    val input = readInputAsText("input/Day03")
    part1(input).println()
    part2(input).println()
}
