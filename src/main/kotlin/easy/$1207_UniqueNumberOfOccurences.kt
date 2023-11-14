package easy

/**
 * Given an array of integers arr, write a function that returns true if and only if
 * the number of occurrences of each value in the array is unique.
 *
 * constraints:
 * - 1 <= arr.length <= 1000
 * - -1000 <= arr[i] <= 1000
 *
 * Example 1:
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation:
 * The value 1 has 3 occurrences, 2 has 2 and 3 has 1.
 *
 * Example 2:
 * Input: arr = [1,2]
 * Output: false
 *
 * Example 3:
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 *
 */
fun main() {
    val arr1 = intArrayOf(1, 2, 2, 1, 1, 3)
    println(`Unique Number of Occurences`(arr1)) // true

    val arr2 = intArrayOf(1, 2)
    println(`Unique Number of Occurences`(arr2)) // false

    val arr3 = intArrayOf(-3, 0, 1, -3, 1, 1, 1, -3, 10, 0)
    println(`Unique Number of Occurences`(arr3)) // true
}

fun `Unique Number of Occurences`(arr: IntArray): Boolean {
    /**
     * create a map to store the frequency of each value in the array
     */
    val freqMap = mutableMapOf<Int, Int>()

    /**
     * iterate through the array and store the frequency of each value in the map
     */
    arr.forEach {
        /**
         * or can also be written using elvis operator:
         * freqMap[it] = (freqMap[it] ?: 0) + 1
         */
        freqMap[it] = freqMap.getOrDefault(it, 0) + 1
    }

    /**
     * create a set to keep track of "visited" frequencies
     */
    val freqSet = mutableSetOf<Int>()

    /**
     * iterate through the map and check if the current frequency is already in the set
     */
    freqMap.forEach {
        /**
         * if the current frequency is already in the set, return false
         */
        if (freqSet.contains(it.value)) {
            return false
        }

        /**
         * otherwise, add the current frequency to the set
         */
        freqSet.add(it.value)
    }

    /**
     * if the iteration completes without returning false, it means all the frequencies are unique
     * so return true
     */
    return true
}