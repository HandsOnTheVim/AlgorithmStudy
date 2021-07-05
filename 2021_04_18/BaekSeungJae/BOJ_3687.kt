package BOJ

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

fun main() {
    BOJ_3687().solution()
}

class BOJ_3687 {
    fun solution() {
        val data = data
        val dp = LongArray(101)
        Arrays.fill(dp, Long.MAX_VALUE)
        dp[2] = 1L
        dp[3] = 7L
        dp[4] = 4L
        dp[5] = 2L
        dp[6] = 6L
        dp[7] = 8L
        dp[8] = 10L
        val appendDp = longArrayOf(0L, 0L, 1L, 7L, 4L, 2L, 0L, 8L)
        for (i in 9..100) {
            for (j in 2..7) {
                val check =
                        StringBuilder().append(dp[i - j]).append(appendDp[j]).toString().toLong()
                dp[i] = dp[i].coerceAtMost(check)
            }
        }
        for (i in 0 until data.problemLength) {
            val chars = CharArray(data.problemArr!![i] / 2)
            Arrays.fill(chars, '1')
            chars[0] = if (data.problemArr!![i] % 2 == 1) '7' else '1'
            println(dp[data.problemArr!![i]].toString() + " " + String(chars))
        }
    }

    val data: Data
        get() {
            var problemLength = 0
            var problemArr: IntArray? = null
            try {
                val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
                var stringTokenizer = StringTokenizer(bufferedReader.readLine())
                problemLength = stringTokenizer.nextToken().toInt()
                problemArr = IntArray(problemLength)
                for (i in 0 until problemLength) {
                    stringTokenizer = StringTokenizer(bufferedReader.readLine())
                    problemArr[i] = stringTokenizer.nextToken().toInt()
                }
            } catch (e: IOException) {
                println("IOE")
            }
            return Data(problemLength, problemArr)
        }

    class Data(var problemLength: Int, var problemArr: IntArray?)
}
