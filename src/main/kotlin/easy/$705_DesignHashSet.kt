package easy

/**
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSet class:
 * - void add(key) Inserts the value key into the HashSet.
 * - bool contains(key) Returns whether the value key exists in the HashSet or not.
 * - void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 *
 * Constraints:
 * - 0 <= key <= 10^6
 * - At most 10^4 calls will be made to add, remove, and contains.
 *
 * Example 1:
 * Input
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 *
 * Explanation
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // return True
 * myHashSet.contains(3); // return False, (not found)
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // return True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // return False, (already removed)
 */
fun main() {
    class MyHashSet {
        val data = BooleanArray(10e6.toInt() + 1)

        fun add(key: Int) {
            data[key] = true
        }

        fun remove(key: Int) {
            data[key] = false
        }

        fun contains(key: Int): Boolean {
            return data[key]
        }
    }

    val myHashSet = MyHashSet()
    myHashSet.add(1)
    myHashSet.add(2)
    println(myHashSet.contains(1)) // true
    println(myHashSet.contains(3)) // false
    myHashSet.add(2)
    println(myHashSet.contains(2)) // true
    myHashSet.remove(2)
    println(myHashSet.contains(2)) // false
}
