package utils.day04

import utils.*
import kotlin.math.pow
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun solveA(fileName: String): Long {
        val input = readInput(fileName)
        val cards = input.map { Card.from(it) }
        return cards.sumOf { it.score }.toLong()
    }

    fun solveB(fileName: String): Long {
        val input = readInput(fileName)
        val cardMatches = input.map { Card.from(it).matches }
        val copyCounts = MutableList(cardMatches.size){1} // each card included
        cardMatches.forEachIndexed { index, matches ->
            (index + 1..index + matches).forEach {
                copyCounts[it] += copyCounts[index]
            }
        }
        return copyCounts.sum().toLong()
    }

    check(solveA("day04/Example") == 13L)
    check(solveB("day04/Example") == 30L)

    val input = "day04/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}

data class Card(val id: Int, val winning: Set<Int>, val numbers: Set<Int>) {
    companion object Factory {
        fun from(string: String): Card { // "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
            val gamePairs = string.splitToPair(": ")
            val id = gamePairs.first.splitToPair(" ").second.trim().toInt()
            val (winningText, numbersText) = gamePairs.second.split(" | ").map { it.trim() }
            val winning = winningText.split(" ")
                .filter { it.isNotEmpty() }
                .map { it.trim().toInt() }
            val numbers = numbersText.split(" ")
                .filter { it.isNotEmpty() }
                .map { it.trim().toInt() }

            return Card(id, winning.toSet(), numbers.toSet())
        }
    }

    val matches: Int = numbers.intersect(winning).count()
    val score: Int = if (matches > 0) 2.toDouble().pow(matches - 1).toInt() else 0
}