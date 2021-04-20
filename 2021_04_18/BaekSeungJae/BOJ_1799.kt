package BOJ

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


fun main() {
    println(solution())
}

private const val WHITE = 0
private const val BLACK = 1
private var max = 0

fun solution(): Int {
    val data = data
    max = 0
    backtracking(data, 0, WHITE, 0) //흰색 블럭 검사
    val whiteMax = max
    max = 0
    backtracking(data, 0, BLACK, 0) //검은색 블럭 검사
    val blackMax = max
    return whiteMax + blackMax
}

private fun backtracking(data: Data, row: Int, color: Int, count: Int) {
    for (i in row until data.mapLength) {
        for (j in (if (i % 2 == color) 1 else 0) until data.mapLength step 2) {
            if (isPossible(data, i, j)) {
                data.chessMap!![i][j] = 2
                data.crossLeft[i + j]++ // '/'대각선 카운트
                data.crossRight[i - j + data.mapLength - 1]++ // '\'대각선 카운트
                backtracking(data, i, color, count + 1)
                data.crossLeft[i + j]--
                data.crossRight[i - j + data.mapLength - 1]--
                data.chessMap!![i][j] = 1
            }
        }
    }

    max = max.coerceAtLeast(count)
}

private fun isPossible(data: Data, row: Int, col: Int): Boolean {
    if (data.chessMap!![row][col] != 1) {
        return false
    } //놓을 수 없는 자리

    try {
        if (data.crossLeft[row + col] > 0 || data.crossRight[row - col + data.mapLength - 1] > 0) {
            return false
        } //같은 대각선에 비숍이 있을 때
    } catch (e: NullPointerException) {
        println("$row,$col")
    }

    return true
}

val data: Data
    get() {
        var mapLength = 0
        var chessMap: Array<IntArray>? = null
        try {
            BufferedReader(InputStreamReader(System.`in`)).use { bufferedReader ->
                mapLength = bufferedReader.readLine().toInt()
                chessMap = Array(mapLength) { IntArray(mapLength) }
                for (i in 0 until mapLength) {
                    val stringTokenizer = StringTokenizer(bufferedReader.readLine())
                    for (j in 0 until mapLength) {
                        chessMap!![i][j] = Integer.valueOf(stringTokenizer.nextToken())
                    }
                }
            }
        } catch (e: IOException) {
            println("IOE")
        } catch (e: NumberFormatException) {
            println("NFE")
        }
        return Data(mapLength, chessMap)
    }

class Data(var mapLength: Int, var chessMap: Array<IntArray>?) {
    var crossLeft: IntArray = IntArray(2 * mapLength - 1)
    var crossRight: IntArray = IntArray(2 * mapLength - 1)
}
