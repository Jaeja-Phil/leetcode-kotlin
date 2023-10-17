package easy

/**
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t
 * (i.e., t is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 * constraints:
 * - 1 <= str1.length, str2.length <= 1000
 * - str1 and str2 consist of English uppercase letters.
 *
 * Example 1:
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 *
 * Example 2:
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 *
 * Example 3:
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 */
fun main() {
    val res1 = `Greatest Common Divisor of Strings`("ABCABC", "ABC")
    println(res1) // ABC

    val res2 = `Greatest Common Divisor of Strings`("ABABAB", "ABAB")
    println(res2) // AB

    val res3 = `Greatest Common Divisor of Strings`("LEET", "CODE")
    println(res3) //
}

fun `Greatest Common Divisor of Strings`(str1: String, str2: String): String {
    /**
     * Check if greatest common divisor exists
     * if str1 + str2 != str2 + str1, then there is no greatest common divisor
     * ex) "ABC" + "ABCABC" != "ABCABC" + "ABC" -> "ABCABCABC" != "ABCABCABC" -> no GCD
     */
    if (str1 + str2 != str2 + str1) {
        return ""
    }

    /**
     * declare two variables to store the length of two strings
     */
    var l1 = str1.length
    var l2 = str2.length

    /**
     * Euclidean algorithm
     * - https://en.wikipedia.org/wiki/Euclidean_algorithm
     * - subtract the smaller number from the larger number until both numbers become the same
     * - the number that both numbers become is the greatest common divisor
     * ex) "ABCABC" and "ABC" --> 6 != 3 --> 3 == 3 --> GCD = 3
     * ex) "ABABAB" and "ABAB" --> 6 != 4 --> 2 != 4 --> 2 == 2 --> GCD = 2
     */
    while (l1 != l2) {
        if (l1 > l2) l1 -= l2
        else l2 -= l1
    }

    /**
     * return the substring of str1 from index 0 to l1 (could be l2 as well since l1 == l2)
     */
    return str1.substring(0, l1)
}
