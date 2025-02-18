package medium

/**
 * You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more
 * non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s
 * which are not present in any of the substrings.
 *
 * Return the minimum number of extra characters left over if you break up s optimally.
 *
 * Constraints:
 * - 1 <= s.length <= 50
 * - 1 <= dictionary.length <= 50
 * - 1 <= dictionary[i].length <= 50
 * - s and dictionary[i] consist of lowercase English letters.
 * - dictionary contains distinct words
 *
 * Example 1:
 * Input: s = "leetscode", dictionary = ["leet", "code", "leetcode"]
 * Output: 1
 * Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only
 * 1 unused character (at index 4), so we return 1.
 *
 * Example 2:
 * Input: s = "sayhelloworld", dictionary = ["hello","world"]
 * Output: 3
 * Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The
 * characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence,
 * we return 3.
 */
fun main() {
    class Trie {
        inner class TrieNode {
            val children = Array<TrieNode?>(26) { null }
            var isEnd = false
        }

        val root = TrieNode()

        fun insert(word: String) {
            var node = root
            for (char in word) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    node.children[index] = TrieNode()
                }
                node = node.children[index]!!
            }
            node.isEnd = true
        }
    }

    fun minExtraChar(s: String, dictionary: Array<String>): Int {
        val trie = Trie()
        for (word in dictionary) {
            trie.insert(word)
        }

        val dp = mutableMapOf(s.length to 0)
        fun dfs(index: Int): Int {
            if (index in dp) {
                return dp[index]!!
            }

            var result = 1 + dfs(index + 1)
            var node = trie.root
            for (i in index until s.length) {
                val char = s[i]
                val charIndex = char - 'a'
                if (node.children[charIndex] == null) {
                    break
                }
                node = node.children[charIndex]!!
                if (node.isEnd) {
                    result = minOf(result, dfs(i + 1))
                }
            }

            dp[index] = result
            return result
        }

        return dfs(0)
    }

    println(
        minExtraChar("leetscode", arrayOf("leet", "code", "leetcode")) // 1
    ) // 1

    println(
        minExtraChar("sayhelloworld", arrayOf("hello", "world")) // 3
    ) // 3
}