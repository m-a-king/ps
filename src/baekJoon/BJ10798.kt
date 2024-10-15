package baekJoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    val tempArr = Array(5) { mutableListOf<Char>() }
    var maxCol = 0;

    for (i in 0 until 5) {
        val splitWords = br.readLine().toCharArray()
        maxCol = max(maxCol, splitWords.size)

        for (j in splitWords) {
            tempArr[i].add(j)
        }
    }

    for (col in 0 until  maxCol) {
        for (row in 0 until 5) {
            sb.append(tempArr[row].getOrNull(col) ?: "")
        }
    }

    println(sb)
}