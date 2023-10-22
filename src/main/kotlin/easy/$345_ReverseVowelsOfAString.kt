package easy

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 * constraints:
 * - 1 <= s.length <= 3 * 10^5
 * - s consist of printable ASCII characters.
 *
 * Example 1:
 * Input: s = "hello"
 * Output: "holle"
 *
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 */
fun main() {
    val res1 = `Reverse Vowels of a String`("hello")
    println(res1) // holle

    val res2 = `Reverse Vowels of a String`("leetcode")
    println(res2) // leotcede
}

fun `Reverse Vowels of a String`(s: String): String {
    /**
     * create a set of vowels for efficient vowel check (O(1))
     */
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

    /**
     * create two pointers that starts from left and right ends of given string "s"
     */
    var left = 0
    var right = s.lastIndex

    /**
     * convert the given string to a char array for efficient swapping (O(1))
     */
    val chrs = s.toCharArray()

    /**
     * iterate through the string until the left pointer meets the right pointer
     */
    while (left < right) {
        /**
         * set left pointer until it reaches a vowel
         */
        while (left < right && chrs[left] !in vowels) {
            left++
        }
        /**
         * set right pointer until it reaches a vowel
         */
        while (left < right && chrs[right] !in vowels) {
            right--
        }

        /**
         * if left pointer is less than right pointer, it means that both pointers are pointing at vowels
         * and they can be swapped (since the vowel to be swapped hasn't been swapped yet)
         */
        if (left < right) {
            /**
             * use .also{} to swap the vowels in place
             * this is equivalent to:
             * chrs[left] = 'character at chrs[right]'.also {
             *   chrs[right] = chrs[left] // <-- left hasn't been swapped yet
             * }
             */
            chrs[left] = chrs[right].also { chrs[right] = chrs[left] }
        }

        /**
         * move the pointers to the next characters
         */
        left++
        right--
    }

    /**
     * finally, return the string representation of the char array
     */
    return String(chrs)
}