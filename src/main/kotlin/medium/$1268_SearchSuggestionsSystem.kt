package medium

/**
 * You are given an array of strings products and a string searchWord.
 *
 * Design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with searchWord. If there are more than three products with a common
 * prefix return the three lexicographically minimums products.
 *
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 * Constraints:
 * - 1 <= products.length <= 1000
 * - 1 <= products[i].length <= 3000
 * - 1 <= sum(products[i].length) <= 2 * 10^4
 * - All the strings of products are unique.
 * - products[i] consists of lowercase English letters.
 * - 1 <= searchWord.length <= 1000
 * - searchWord consists of lowercase English letters.
 *
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
 *
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Explanation: The only word "havana" will be always suggested while typing the search word.
 */
fun main() {
    class Trie {
        val root = TrieNode()

        inner class TrieNode {
            var children = mutableMapOf<Char, TrieNode>()
            var isEndOfWord = false
        }

        fun insert(word: String) {
            var current = root
            for (char in word) {
                current = current.children.getOrPut(char) { TrieNode() }
            }
            current.isEndOfWord = true
        }

        fun search(word: String): Boolean {
            var current = root
            for (char in word) {
                current = current.children[char] ?: return false
            }
            return current.isEndOfWord
        }

        fun startsWith(prefix: String): Boolean {
            var current = root
            for (char in prefix) {
                current = current.children[char] ?: return false
            }
            return true
        }
    }

    fun dfs(node: Trie.TrieNode, prefix: String, suggestions: MutableList<String>) {
        if (suggestions.size == 3) return
        if (node.isEndOfWord) suggestions.add(prefix)
        node.children.forEach { (char, child) -> dfs(child, prefix + char, suggestions) }
    }

    fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        val trie = Trie()
        products.sorted().forEach { trie.insert(it) }
        val result = mutableListOf<List<String>>()
        var prefix = ""
        var current = trie.root
        for (char in searchWord) {
            prefix += char
            val suggestions = mutableListOf<String>()
            current = current.children[char] ?: break
            dfs(current, prefix, suggestions)
            result.add(suggestions)
        }
        // Fill the rest of the result with empty lists
        repeat(searchWord.length - result.size) { result.add(emptyList()) }

        return result
    }

    val products1 = arrayOf("mobile", "mouse", "moneypot", "monitor", "mousepad")
    val searchWord1 = "mouse"
    println(suggestedProducts(products1, searchWord1)) // Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]

    val products2 = arrayOf("havana")
    val searchWord2 = "havana"
    println(suggestedProducts(products2, searchWord2)) // Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]

    println(suggestedProducts(arrayOf("havana"), "tatiana")) // Output: [[],[],[],[],[],[]]

}