package day01

import utils.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun solveA(fileName: String): Long {
        val input = readInputGroups(fileName)
        val elvesCalories = input.map { calories ->
            calories.sumOf { it.toInt() }
        }
        return elvesCalories
            .max().toLong()
    }

    fun solveB(fileName: String): Long {
        val input = readInputGroups(fileName)
        val elvesCalories = input.map { calories ->
            calories.sumOf { it.toInt() }
        }
        return elvesCalories
            .sortedDescending()
            .take(3)
            .sum().toLong()
    }

    check(solveA("day01/Example") == 24000L)
    check(solveB("day01/Example") == 45000L)

    val input = "day01/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}