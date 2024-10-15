package baekJoon

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    var n = br.readLine().toInt()

    while (n-- > 0) {
        val oxArr = br.readLine().toCharArray()

        var acc = 0;
        var total = 0;
        for (ox in oxArr) {
            if (ox == 'O') {
                total += ++acc;
            } else {
                acc = 0;
            }
        }

        sb.append(total).append("\n")
    }

    println(sb)
}