package day02

import kotlin.math.abs

fun main() {
    val inputText = {}.javaClass.getResource("/day02.txt")!!.readText()
    println("Day 1: ${part1(inputText)}")
    println("Day 2: ${part2(inputText)}")
}


fun part1(inputText: String): Int{
    var safeReports = 0
    var isValid: Boolean
    var isDecreasing: Boolean
    var isIncreasing: Boolean
    inputText.lineSequence().forEach{ line ->
        isValid = false
        isIncreasing = false
        isDecreasing = false
        val numbersArray = line.split(" ")
        for (i in 0 until numbersArray.size - 1){
            if(numbersArray[i].toInt() < numbersArray[i+1].toInt()){
                isIncreasing = true
            }else if(numbersArray[i].toInt() > numbersArray[i+1].toInt()){
                isDecreasing = true
            }else{
                isValid = false
                break
            }
            isValid = abs(numbersArray[i].toInt() - numbersArray[i+1].toInt()) <= 3
            if(!isValid) break
            if(isIncreasing && isDecreasing){
                isValid = false
                break
            }
        }

        if(isValid) safeReports++
    }
    return safeReports
}

fun part2(inputText: String): Int {
    var safeReports = 0

    inputText.lineSequence().forEach { line ->
        val numbersArray = line.split(" ").map { it.toInt() }
        if (validateNumber(numbersArray)) {
            safeReports++
        } else {
            var isValid = false
            for (i in numbersArray.indices) {
                val modifiedArray = numbersArray.toMutableList()
                modifiedArray.removeAt(i)
                if (validateNumber(modifiedArray)) {
                    isValid = true
                    break
                }
            }
            if (isValid) safeReports++
        }
    }
    return safeReports
}

fun validateNumber(numbersArray: List<Int>): Boolean {
    var isIncreasing = false
    var isDecreasing = false

    for (i in 0 until numbersArray.size - 1) {
        if (abs(numbersArray[i] - numbersArray[i + 1]) !in 1..3) {
            return false
        }

        if (numbersArray[i] < numbersArray[i + 1]) {
            isIncreasing = true
        } else if (numbersArray[i] > numbersArray[i + 1]) {
            isDecreasing = true
        }

        if(isDecreasing && isIncreasing) return false
    }

    return true
}
