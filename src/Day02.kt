fun main() {
    fun levelsTest(levels: List<Int>): Boolean {
        var safeAsc = true
        var safeDec = true

        for (i in 0..<levels.size - 1) {
            val current = levels[i]
            val next = levels[i+1]
            val diff = current - next
            if (diff !in 1..3 ) {
                safeAsc = false
            }
            if (diff !in -3..-1) {
                safeDec = false
            }
        }

        return safeAsc || safeDec
    }

    fun part1(input: List<List<Int>>): Int {
        var safeReports = 0
        input.forEach { levels ->
            if (levelsTest(levels)) {
                safeReports++
            }
        }

        return safeReports
    }

    fun part2(input: List<List<Int>>): Int {
        var safeReports = 0
        input.forEach { levels ->
            if (levelsTest(levels)) {
                safeReports++
                return@forEach
            }
            for (i in 0..<levels.size) {
                val test = levels.subList(0, i) + levels.subList(i + 1, levels.size)
                if (levelsTest(test)) {
                    safeReports++
                    return@forEach
                }
            }
        }

        return safeReports
    }



//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)
//
//    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)
//
//    // Read the input from the `src/Day01.txt` file.
//    val input = readInput("Day01")

    val filename = "Day02"
    //val filename = "Day02_test"

    val input = readInput(filename)
    val reports = input.map {
        it.split("""\s+""".toRegex()).map(String::toInt)
    }

    part1(reports).println()
    part2(reports).println()
}
