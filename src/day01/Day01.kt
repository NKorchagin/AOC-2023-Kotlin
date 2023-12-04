package day01

import utils.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun solveA(fileName: String): Long {
        val input = readInput(fileName)
        val values = input.map { value ->
            val digits = value.filter { it.isDigit() }
            "${digits.first()}${digits.last()}".toLong()
        }
        return values.sum()
    }

    fun solveB(fileName: String): Long {
        val input = readInput(fileName)
        return input.sumOf { line ->
            val first = line.findFirstDigit()
            val last = line.findLastDigit()
            first * 10 + last
        }.toLong()
    }

    check(solveA("day01/Example") == 142L)
    check(solveB("day01/Example2") == 281L)

    val input = "day01/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}

fun String.findFirstDigit(): Int {
    return findDigitIndex { strings -> indexOfAny(strings, startIndex = 0, ignoreCase = true) }

}

fun String.findLastDigit(): Int {
    return findDigitIndex { strings -> lastIndexOfAny(strings, startIndex = lastIndex, ignoreCase = true) }
}

fun String.findDigitIndex(function: (strings: Collection<String>) -> Int): Int {
    val idx = function(
        listOf(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
        )
    )
    if (get(idx).isDigit()) {
        return get(idx).digitToInt()
    } else {
        val substring = substring(idx)
        return when {
            substring.startsWith("one") -> 1
            substring.startsWith("two") -> 2
            substring.startsWith("three") -> 3
            substring.startsWith("four") -> 4
            substring.startsWith("five") -> 5
            substring.startsWith("six") -> 6
            substring.startsWith("seven") -> 7
            substring.startsWith("eight") -> 8
            substring.startsWith("nine") -> 9
            else -> error("Unmatched number")
        }
    }
}