package medium

import java.util.PriorityQueue

/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Constraints:
 * - 1 <= s.length <= 500
 * - s consists of lowercase English letters.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: "aba"
 *
 * Example 2:
 * Input: s = "aaab"
 * Output: ""
 */
fun main() {
    fun reorganizeString(s: String): String {
        val count = IntArray(26)
        for (c in s) count[c - 'a']++
        val maxHeap = PriorityQueue { a: Pair<Char, Int>, b: Pair<Char, Int> -> b.second - a.second }
        for (i in count.indices) {
            if (count[i] > 0) {
                maxHeap.add(Pair('a' + i, count[i]))
            }
        }

        val sb = StringBuilder()
        var prev: Pair<Char, Int>? = null

        while (maxHeap.isNotEmpty() || prev != null) {
            if (prev != null && maxHeap.isEmpty()) {
                return ""
            }

            val current = maxHeap.poll()
            sb.append(current.first)

            if (prev != null) {
                maxHeap.offer(prev)
                prev = null
            }

            if (current.second > 1) {
                prev = Pair(current.first, current.second - 1)
            }
        }

        return sb.toString()
    }

    println(reorganizeString("aab")) // "aba"
    println(reorganizeString("aaab")) // ""
}