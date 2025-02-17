package medium

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome
 * Return all possible palindrome partitioning of s.
 *
 * Constraints:
 * - 1 <= s.length <= 16
 * - s contains only lowercase English letters.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 */
fun main() {
    fun partition(s: String): List<List<String>> {
        val result = mutableListOf<List<String>>()

        fun isPalindrome(start: Int, end: Int): Boolean {
            var left = start
            var right = end
            while (left < right) {
                if (s[left] != s[right]) {
                    return false
                }
                left++
                right--
            }
            return true
        }

        fun dfs(start: Int, path: MutableList<String>) {
            if (start == s.length) {
                result.add(path.toList())
                return
            }

            for (end in start..<s.length) {
                if (isPalindrome(start, end)) {
                    path.add(s.substring(start, end + 1))
                    dfs(end + 1, path)
                    path.removeAt(path.lastIndex)
                }
            }
        }

        dfs(0, mutableListOf())

        return result
    }

    println(partition("aab")) // [[a, a, b], [aa, b]]
    println(partition("a")) // [[a]]
}
