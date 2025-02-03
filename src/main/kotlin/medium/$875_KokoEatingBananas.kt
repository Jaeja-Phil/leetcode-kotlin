package medium

import kotlin.math.ceil

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone
 * and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k
 * bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any
 * more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * constraints:
 * - 1 <= piles.length <= 10^4
 * - piles.length <= h <= 10^9
 * - 1 <= piles[i] <= 10^9
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 */
fun main() {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        // Solution 1.
//        // sort the piles array
//        piles.sort()
//
//        // get the size of the piles array
//        val pilesSize = piles.size
//
//        var left = 1
//        var right = piles[pilesSize - 1]
//        while (left < right) {
//            val mid = left + (right - left) / 2
//            var hours = 0
//            for (i in 0 until pilesSize) {
//                hours += (piles[i] + mid - 1) / mid
//            }
//            if (hours > h) {
//                left = mid + 1
//            } else {
//                right = mid
//            }
//        }
//
//        return left

        // Solution 2.
        piles.sort()
        var left = 1
        var right = piles.max()
        var res = right

        while (left <= right) {
            val mid = left + (right - left) / 2
            var hours = 0L
            for (pile in piles) {
                hours += ceil(pile.toDouble() / mid).toLong()
            }

            if (hours > h) {
                // if too much time was taken, need to increase the speed, shift left
                left = mid + 1
            } else {
                // if time taken is less than or equal to h, need to decrease the speed, shift right
                res = mid
                right = mid - 1
            }
        }

        return res
    }

//    val result = minEatingSpeed(intArrayOf(3, 6, 7, 11), 8)
//    println(result) // 4
//
//    val result2 = minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 5)
//    println(result2) // 30
//
//    val result3 = minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 6)
//    println(result3) // 23

    val result4 = minEatingSpeed(intArrayOf(805306368,805306368,805306368), 1000000000)
    println(result4) // 3
}