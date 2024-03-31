package medium

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve
 * keys in a dataset of strings. There are various applications of this data structure, such as autocomplete
 * and spellchecker.
 *
 * Implement the Trie class:
 * - Trie() Initializes the trie object.
 * - void insert(String word) Inserts the string word into the trie.
 * - boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before),
 *   and false otherwise.
 * - boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the
 *   prefix "prefix", and false otherwise.
 *
 * Constraints:
 * - 1 <= word.length, prefix.length <= 2000
 * - word and prefix consist only of lowercase English letters.
 *
 * Example 1:
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 */
fun main() {
    class Trie {
        private val root = TrieNode()

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

    val trie = Trie()
    trie.insert("apple")
    println(trie.search("apple")) // return True
    println(trie.search("app")) // return False
    println(trie.startsWith("app")) // return True
    trie.insert("app")
    println(trie.search("app")) // return True
}