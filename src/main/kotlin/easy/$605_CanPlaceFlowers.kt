package easy

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
 * and an integer n, return true if n new flowers can be planted in the flowerbed without violating
 * the no-adjacent-flowers rule and false otherwise.
 *
 * constraints:
 * - 1 <= flowerbed.length <= 2 * 10^4
 * - flowerbed[i] is 0 or 1.
 * - There are no two adjacent flowers in flowerbed.
 * - 0 <= n <= flowerbed.length
 */
fun main() {
    val res1 = `Can Place Flowers`(intArrayOf(1, 0, 0, 0, 1), 1)
    println(res1) // true

    val res2 = `Can Place Flowers`(intArrayOf(1, 0, 0, 0, 1), 2)
    println(res2) // false
}

fun `Can Place Flowers`(flowerbed: IntArray, n: Int): Boolean {
    /**
     * init a variable to store the number of plots that can be planted
     * - this number will be decremented by 1 when a plot is planted
     */
    var plots = n

    /**
     * iterate through the flowerbed
     * iterate condition:
     * - index should be within the range of the flowerbed
     * - plots should be greater than 0 (there is a leftover plant to be planted)
     */
    var i = 0
    while (i < flowerbed.size && plots > 0) {
        /**
         * check if the current plot is empty and the adjacent plots are empty
         */
        if (flowerbed[i] == 0 && // checking current plot's emptiness
            (i - 1 < 0 || flowerbed[i - 1] == 0) && // checking previous plot's emptiness
            (i + 1 >= flowerbed.size || flowerbed[i + 1] == 0)) { // checking next plot's emptiness
            // flower is planted in the current plot
            plots--
            flowerbed[i] = 1
            // skip the next plot since it cannot be planted
            i++
        }
        // move to the next plot
        i++
    }

    return plots == 0
}