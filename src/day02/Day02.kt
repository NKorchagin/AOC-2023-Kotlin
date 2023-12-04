package utils.day02

import utils.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun solveA(fileName: String): Long {
        val input = readInput(fileName)
        val games = input.map { Game.from(it) }
        return games.sumOf { if (it.isValid) it.number else 0 }.toLong()
    }

    fun solveB(fileName: String): Long {
        val input = readInput(fileName)
        val games = input.map { Game.from(it) }
        return games.sumOf { it.power.toLong() }
    }

    check(solveA("day02/Example") == 8L)
    check(solveB("day02/Example") == 2286L)

    val input = "day02/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}

data class Party(val red: Int, val green: Int, val blue: Int) {
    companion object Factory {
        fun from(string: String): Party { // "6 blue, 2 red, 1 green"
            val pairs = string.split(", ").map { it.splitToPair(" ") }
            var red = 0
            var green = 0
            var blue = 0
            for (pair in pairs) {
                when (pair.second) {
                    "red" -> red = pair.first.toInt()
                    "green" -> green = pair.first.toInt()
                    "blue" -> blue = pair.first.toInt()
                }
            }
            return Party(red, green, blue)
        }
    }
}

data class Game(val number: Int,val parties: List<Party>) {
    companion object Factory {
        fun from(string: String): Game { // "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
            val gamePairs = string.splitToPair(": ")
            val number = gamePairs.first.splitToPair(" ").second.toInt()
            val parties = gamePairs.second.split("; ").map { Party.from(it) }
            return Game(number, parties)
        }
    }

    val isValid: Boolean
        get() = null == parties.find { it.red > 12 || it.green > 13 || it.blue > 14 }

    val power: Int
        get() {
            val requiredRed = parties.maxOf { it.red }
            val requiredBlue = parties.maxOf { it.blue }
            val requiredGreen = parties.maxOf { it.green }
            return requiredRed * requiredGreen * requiredBlue
        }
}