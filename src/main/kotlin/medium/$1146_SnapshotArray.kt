package medium

import java.util.TreeMap

/**
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * Constraints:
 * - 1 <= length <= 50000
 * - At most 50000 calls will be made to set, snap, and get.
 * - 0 <= index < length
 * - 0 <= snap_id < (the total number of times we called snap())
 * - 0 <= val <= 10^9
 *
 * Example 1:
 * Input
 * ["SnapshotArray", "set", "snap", "set", "get"]
 * [[3], [0, 5], [], [0, 6], [0, 0]]
 * Output
 * [null, null, 0, null, 5]
 * Explanation
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0, 5);  // Set array[0] = 5
 * snapshotArr.snap();     // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0, 6);  // Set array[0] = 6
 * snapshotArr.get(0, 0);  // Get the value of array[0] with snap_id = 0, return 5
 */
fun main() {
    class SnapShotArray(length: Int) {
        private var snapId = 0
        private val historyRecords: MutableList<TreeMap<Int, Int>> = MutableList(length) { TreeMap() }

        fun set(index: Int, value: Int) {
            historyRecords[index][snapId] = value
        }

        fun snap(): Int {
            return snapId++
        }

        fun get(index: Int, snapId: Int): Int {
            val historyRecord = historyRecords[index]
            val snapEntry = historyRecord.floorEntry(snapId)
            return snapEntry?.value ?: 0
        }
    }

    val snapshotArr = SnapShotArray(3)
    snapshotArr.set(0, 5)
    println(snapshotArr.snap()) // 0
    snapshotArr.set(0, 6)
    println(snapshotArr.get(0, 0)) // 5
    println("--------------------")
    val snapShotArr2 = SnapShotArray(1)
    snapShotArr2.set(0, 15)
    println(snapShotArr2.snap()) // 0
    println(snapShotArr2.snap()) // 1
    println(snapShotArr2.snap()) // 2
    println(snapShotArr2.get(0, 2)) // 15
    println(snapShotArr2.snap()) // 3
    println(snapShotArr2.snap()) // 4
    println(snapShotArr2.get(0, 0)) // 15
}