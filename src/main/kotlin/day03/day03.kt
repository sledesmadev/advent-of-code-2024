package day03

fun main() {
    val inputText = {}.javaClass.getResource("/day03.txt")!!.readText()
    println("Day 1: ${part1(inputText)}")
    println("Day 2: ${part2(inputText)}")
}


fun part1(inputText: String): Int{
    var result = 0
    val regex = Regex("mul\\((\\d+),(\\d+)\\)")
    val matches = regex.findAll(inputText)
    val names = matches.map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }

    names.forEach {
        if(it.first <= 999 && it.second <= 999){
            result += it.first * it.second
        }
    }

    return result
}

fun part2(inputText: String): Int {
    //Get al indexes of do and don't
    val regexDo = Regex("do\\(\\)|don't\\(\\)")
    val matchesDo = regexDo.findAll(inputText)

    val indexesDo = matchesDo.map { it.range.first }
    val indexMap = mutableMapOf<Int, Pair<Int, Int>>()

    //Create map with coordinates
    var startIndex = 0
    indexesDo.forEachIndexed { index, r ->
        indexMap[index] = startIndex to r
        startIndex = r

        if(index == indexesDo.toList().size-1){
            indexMap[index+1] = r to inputText.length
        }
    }

    //Remove coordinates that contains a don't
    val filteredMap = indexMap
        .filterNot { key -> inputText.substring(key.component2().first, key.component2().second).contains("don't") }

    var sum = 0
    filteredMap.forEach{
        sum += part1(inputText.substring(it.component2().first, it.component2().second))
    }

    return sum
}
