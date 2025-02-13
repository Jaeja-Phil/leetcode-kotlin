package medium

import java.util.*

/**
 * A string s is called happy if it satisfies the following conditions:
 * - s only contains the letters 'a', 'b', and 'c'.
 * - s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * - s contains at most a occurrences of the letter 'a'.
 * - s contains at most b occurrences of the letter 'b'.
 * - s contains at most c occurrences of the letter 'c'.
 *
 * Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy
 * strings, return any of them. If there is no such string, return the empty string "".
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Constraints:
 * - 0 <= a, b, c <= 100
 * - a + b + c > 0
 *
 * Example 1:
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 *
 * Example 2:
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 */
fun main() {
    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        // first: char, second: count
        val maxHeap = PriorityQueue<Pair<Char, Int>>(compareByDescending { it.second })
        if (a > 0) maxHeap.add('a' to a)
        if (b > 0) maxHeap.add('b' to b)
        if (c > 0) maxHeap.add('c' to c)

        val sb = StringBuilder()
        while (maxHeap.isNotEmpty()) {
            var (char, count) = maxHeap.poll()
            if (sb.length >= 2 && sb[sb.length - 1] == char && sb[sb.length - 2] == char) {
                if (maxHeap.isEmpty()) {
                    break
                }

                var (nextChar, nextCount) = maxHeap.poll()
                sb.append(nextChar)
                nextCount -= 1
                if (nextCount != 0) {
                    maxHeap.add(nextChar to nextCount)
                }
            } else {
                sb.append(char)
                count -= 1
            }

            if (count != 0) {
                maxHeap.add(char to count)
            }
        }

        return sb.toString()
    }

    println(longestDiverseString(1, 1, 7)) // ccaccbcc
    println(longestDiverseString(7, 1, 0)) // aabaa
}