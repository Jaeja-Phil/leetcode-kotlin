package easy

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * You call a pre-defined API int guess(int num), which returns 3 possible results:
 * - -1: The number I picked is lower than your guess (i.e. pick < num).
 * - 1: The number I picked is higher than your guess (i.e. pick > num).
 * - 0: The number I picked is equal to your guess (i.e. pick == num).
 * Return the number that I picked.
 *
 * constraints:
 * - 1 <= n <= 2^31 - 1
 * - 1 <= pick <= n
 *
 * Example 1:
 * Input: n = 10, pick = 6
 * Output: 6
 *
 * Example 2:
 * Input: n = 1, pick = 1
 * Output: 1
 *
 * Example 3:
 * Input: n = 2, pick = 1
 * Output: 1
 */
fun main() {
    abstract class GuessGame {
        fun guess(num: Int): Int {
            return 0
        }

        abstract fun guessNumber(n: Int): Int
    }

    class Solution : GuessGame() {
        override fun guessNumber(n: Int): Int {
            // Binary search approach
//            var left = 1
//            var right = n
//            while (left < right) {
//                val mid = left + (right - left) / 2
//                when {
//                    guess(mid) == 0 -> return mid
//                    guess(mid) == 1 -> left = mid + 1
//                    else -> right = mid
//                }
//            }
//            return left

            // Ternary search approach
            var left = 1
            var right = n

            while (true) {
                val mid1 = left + (right - left) / 3
                val mid2 = right - (right - left) / 3

                when {
                    guess(mid1) == 0 -> return mid1
                    guess(mid2) == 0 -> return mid2
                    guess(mid1) + guess(mid2) == 0 -> {
                        left = mid1 + 1
                        right = mid2 - 1
                    }
                    guess(mid1) == -1 -> {
                        right = mid1 - 1
                    }
                    else -> left = mid2 + 1
                }
            }
        }
    }
}