package utils

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name").readLines()

fun readInputText(name: String) = Path("src/$name")
    .readText()
    .dropLastWhile { it == '\n' }

fun readInputGroups(name: String) = Path("src/$name")
    .readText()
    .dropLastWhile { it == '\n' }
    .split("\n" + "\n")
    .map { it.split("\n") }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun IntRange.inside(range: IntRange) = first in range && last in range
fun IntRange.overlaps(range: IntRange) = first in range || last in range

fun String.splitToPair(delimiter: String) = split(delimiter).let { it.first() to it.last() }


