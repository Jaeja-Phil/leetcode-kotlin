package easy

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The
 * order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographically in this alien language.
 *
 * Constraints:
 * - 1 <= words.length <= 100
 * - 1 <= words[i].length <= 20
 * - order.length == 26
 * - All characters in words[i] and order are English lowercase letters.
 *
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldab
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to
 * lexicographical rules, "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less
 * than any other character
 */
fun main() {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val orderMap = order.withIndex().associate { it.value to it.index }
        fun isSorted(first: String, second: String): Boolean {
            val minLen = minOf(first.length, second.length)
            for (i in 0..<minLen) {
                if (orderMap[first[i]]!! < orderMap[second[i]]!!) {
                    return true
                } else if (orderMap[first[i]]!! > orderMap[second[i]]!!) {
                    return false
                }
            }

            return first.length <= second.length
        }

        for (i in 0 until words.size - 1) {
            if (!isSorted(words[i], words[i + 1])) {
                return false
            }
        }

        return true
    }

    println(isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz")) // true
    println(isAlienSorted(arrayOf("word", "world", "row"), "worldab")) // false
    println(isAlienSorted(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz")) // false
}