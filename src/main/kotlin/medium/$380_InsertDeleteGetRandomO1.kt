package medium

/**
 * Implement the RandomizedSet class:
 * - RandomizedSet() Initializes the RandomizedSet object.
 * - bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present,
 *   false otherwise.
 * - bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false
 *   otherwise.
 * - int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one
 *   element exists when this method is called). Each element must have the same probability of being returned.
 *
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * Constraints:
 * - -2^31 <= val <= 2^31 - 1
 * - At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
 * - There will be at least one element in the data structure when getRandom is called.
 *
 * Example 1:
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 */
fun main() {
    class RandomizedSet() {
        private val set = mutableSetOf<Int>()

        fun insert(`val`: Int): Boolean {
            return set.add(`val`)
        }

        fun remove(`val`: Int): Boolean {
            return set.remove(`val`)
        }

        fun getRandom(): Int {
            return set.random()
        }
    }

    val randomizedSet = RandomizedSet()
    println(randomizedSet.insert(1)) // true
    println(randomizedSet.remove(2)) // false
    println(randomizedSet.insert(2)) // true
    println(randomizedSet.getRandom()) // 2
    println(randomizedSet.remove(1)) // true
    println(randomizedSet.insert(2)) // false
    println(randomizedSet.getRandom()) // 2
}
