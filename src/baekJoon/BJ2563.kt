package baekJoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    var n = br.readLine().toInt()
    val totalArea = n * 100


    val map = Array(101) { IntArray(101) }

    while (n-- > 0) {
        val split = StringTokenizer(br.readLine())
        val left = split.nextToken().toInt()
        val bottom = split.nextToken().toInt()

        for (i in left until  (left + 10)) {
            for (j in bottom until  (bottom + 10)) {
                map[i][j]++
            }
        }
    }

    var overlap = 0;

    for (i in 1..100) {
        for (j in 1..100) {
            if (map[i][j] > 1) {
                overlap += map[i][j]-1
            }
        }
    }

    println(totalArea - overlap)
}