package day01

import kotlin.math.abs

fun main() {
    val inputText = {}.javaClass.getResource("/day01.txt")!!.readText()
    println("Day 1: ${part1(inputText)}")
    println("Day 2: ${part2(inputText)}")
}


fun part1(inputText: String): Int{
    val firstColumList = ArrayList<Int>()
    val secondColumList = ArrayList<Int>()
    var sum = 0
    inputText.lineSequence().forEach { line ->
        firstColumList.add(line.substring(0, line.indexOfFirst(Char::isWhitespace)).toInt())
        secondColumList.add(line.substring(line.indexOfLast(Char::isWhitespace)+1, line.length).toInt())
    }

    firstColumList.sort()
    secondColumList.sort()

    for(i in firstColumList.indices){
        sum += abs(secondColumList[i] - firstColumList[i])
    }

    return sum
}

fun part2(inputText: String): Int {
    val firstColumList = ArrayList<Int>()
    val secondColumList = ArrayList<Int>()
    var sum = 0

    inputText.lineSequence().forEach { line ->
        firstColumList.add(line.substring(0, line.indexOfFirst(Char::isWhitespace)).toInt())
        secondColumList.add(line.substring(line.indexOfLast(Char::isWhitespace)+1, line.length).toInt())
    }

    for(i in firstColumList.indices){
        sum += firstColumList[i] * filterByNumberCount(secondColumList, firstColumList[i])
    }

    return sum

}

fun filterByNumberCount(secondColumList: List<Int>, value: Int): Int {
    return secondColumList
        .stream()
        .filter { i-> i == value }
        .count()
        .toInt()
}