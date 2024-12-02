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

    fun levelsTestZipWithNext(levels: List<Int>): Boolean {
        val diffs = levels.zipWithNext { a, b -> a - b }
        return diffs.all { it in 1..3 } || diffs.all { it in -3..-1}
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

    fun part1ZipWithNext(input: List<List<Int>>): Int {
        return input.count { levelsTestZipWithNext(it) }
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

    fun part2Indices(input: List<List<Int>>): Int {
        val dampened = { levels: List<Int>, index: Int ->
            levels.subList(0, index) + levels.subList(index + 1, levels.size)
        }

        return input.count { levels ->
            levelsTestZipWithNext(levels) || levels.indices.any { levelsTestZipWithNext(dampened(levels, it)) }
        }
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
        parseLineToListInt(it)
    }

    part1(reports).println()
    part1ZipWithNext(reports).println()
    part2(reports).println()
    part2Indices(reports).println()
}
