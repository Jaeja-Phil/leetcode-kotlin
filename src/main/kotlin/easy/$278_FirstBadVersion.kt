package easy

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
 * following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 *
 * Constraints:
 * - 1 <= bad <= n <= 2^31 - 1
 *
 * Example 1:
 * Input: n = 5, bad = 4
 * Output: 4
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 *
 * Example 2:
 * Input: n = 1, bad = 1
 * Output: 1
 */
fun main() {
    abstract class VersionControl {
        fun isBadVersion(version: Int): Boolean {
            return version == 4
        }

        abstract fun firstBadVersion(n: Int): Int
    }

    class Solution : VersionControl() {
        override fun firstBadVersion(n: Int): Int {
            var left = 1
            var right = n
            while (left <= right) {
                val mid = left + (right - left) / 2
                if (isBadVersion(mid)) {
                    // all versions after and including mid are bad, slide left window of search boundary
                    right = mid - 1
                } else {
                    // all versions up to current(mid) is good, slide left to mid
                    left = mid + 1
                }
            }

            return left
        }
    }

    val solution = Solution()
    println(solution.firstBadVersion(5)) // 4
//    println(solution.firstBadVersion(1)) // 1
}