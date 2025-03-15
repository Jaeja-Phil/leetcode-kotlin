package medium

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to
 *   the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Constraints:
 * - 1 <= capacity <= 3000
 * - 0 <= key <= 10^4
 * - 0 <= value <= 10^5
 * - At most 2 * 10^5 calls will be made to get and put.
 *
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
fun main() {
    data class Node(
        var key: Int,
        var value: Int,
        var before: Node? = null,
        var after: Node? = null,
    )

    class LRUCache(
        val capacity: Int,
    ) {
        // tail and head
        // tail: LRU
        // head: MRU
        // head <-> node <-> node <-> tail
        val head = Node(-1, -1)
        val tail = Node(-1, -1)
        val map = mutableMapOf<Int, Node>() // key: key, value: Node of wanted

        init {
            head.after = tail
            tail.before = head
        }

        fun insert(node: Node) {
            val first = head.after!!

            head.after = node
            node.after = first
            node.before = head
            first.before = node
        }

        fun delete(node: Node) {
            val beforeNode = node.before
            val afterNode = node.after

            beforeNode?.after = afterNode
            afterNode?.before = beforeNode
        }

        fun get(key: Int): Int {
            if (key !in map) {
                return -1
            }

            val node = map[key]!!
            delete(node)
            insert(node)

            return node.value
        }

        fun put(
            key: Int,
            value: Int,
        ) {
            if (map[key] == null) {
                val node = Node(key, value)
                insert(node)
                map[key] = node

                if (map.size > capacity) {
                    val targetNode = tail.before!!
                    delete(targetNode)
                    map.remove(targetNode.key)
                }
                return
            }

            val node = map[key]!!
            node.value = value
            delete(node)
            insert(node)
        }
    }

    val lRUCache = LRUCache(2)
    lRUCache.put(1, 1)
    lRUCache.put(2, 2)
    println(lRUCache.get(1)) // 1
    lRUCache.put(3, 3)
    println(lRUCache.get(2)) // -1
    lRUCache.put(4, 4)
    println(lRUCache.get(1)) // -1
    println(lRUCache.get(3)) // 3
    println(lRUCache.get(4)) // 4
}