package programmers.level3

import java.util.*


fun main() {
    val begin1 = "hit"
    val target1 = "cog"
    val words1 = arrayOf("hot", "dot", "dog", "lot", "log", "cog")
    println(solution(begin1, target1, words1))

    val begin2 = "hit"
    val target2 = "cog"
    val words2 = arrayOf("hot", "dot", "dog", "lot", "log")
    println(solution(begin2, target2, words2))
}

fun solution(begin: String, target: String, words: Array<String>): Int {
    var answer = 0
    val isVisited = BooleanArray(words.size)
    val wordList = listOf(*words)
    if (!wordList.contains(target)) { // target 단어 안가지고 있는 경우
        return 0
    }
    val queue = LinkedList<Word>()
    queue.offer(Word(begin, 0))

    while (!queue.isEmpty()) {
        with(queue.poll()) {
            if (this.word == target) {
                answer = this.cnt
                return answer
            }
            for (i in words.indices) {
                if (!isVisited[i] && isConvert(this.word, words[i])) {
                    isVisited[i] = true
                    queue.offer(Word(words[i], this.cnt + 1))
                }
            }
        }
    }
    return answer
}

fun isConvert(word: String, convertWord: String): Boolean {
    var count = 0
    for (i in word.indices) {
        if (word[i] != convertWord[i]) {
            count++
        }
        if (count > 1) {
            return false
        }
    }
    return true
}

data class Word(var word: String, var cnt: Int)
