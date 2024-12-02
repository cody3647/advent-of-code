import kotlin.math.abs

fun main() {
    fun part1(firstCol: List<Long>, secondCol: List<Long>): Long {
        val secondColSorted = secondCol.sorted()

        return firstCol.sorted().mapIndexed { index, item ->
            abs(item - secondColSorted[index])
        }.sum()
    }

    fun part1Zip(firstCol: List<Long>, secondCol: List<Long>): Long {
        return firstCol.sorted().zip(secondCol.sorted()).sumOf { abs(it.first - it.second)}
    }

    fun part2(firstCol: List<Long>, secondCol: List<Long>): Long {
        val counts = mutableMapOf<Long, Long>()
        secondCol.forEach {
            counts.merge(it, 1, Long::plus)
        }

        return firstCol.sumOf { counts.getOrDefault(it, 0) * it }
    }

    fun part2Groupingby(firstCol: List<Long>, secondCol: List<Long>): Long {
        val counts = secondCol.groupingBy { it }.eachCount()

        return firstCol.sumOf { counts.getOrDefault(it, 0) * it }
    }

    val input = readInput("Day01")
    val (first, second) = input.map {
        Pair(it.substringBefore(' ').toLong(), it.substringAfterLast(' ').toLong())
    }.unzip()

    part1(first, second).println()
    part1Zip(second, first).println()
    part2(first, second).println()
    part2Groupingby(first, second).println()

}
