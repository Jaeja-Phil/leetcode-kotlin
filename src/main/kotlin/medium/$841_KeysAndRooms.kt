package medium

import java.util.*

/**
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit
 * all the rooms. However, you cannot enter a locked room without having its key.
 *
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room
 * it unlocks, and you can take all of them with you to unlock the other rooms.
 *
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if
 * you can visit all the rooms, or false otherwise.
 *
 * constraints:
 * - n == rooms.length
 * - 2 <= n <= 1000
 * - 0 <= rooms[i].length <= 1000
 * - 1 <= sum(rooms[i].length) <= 3000
 * - 0 <= rooms[i][j] < n
 * - All the values of rooms[i] are unique.
 *
 * Example 1:
 * Input: rooms = [[1],[2],[3],[]]
 * Output: true
 *
 * Example 2:
 * Input: rooms = [[1,3],[3,0,1],[2],[0]]
 * Output: false
 */
fun main() {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        /**
         * create a set to keep track of
         * - visited rooms
         * - inspected rooms
         */
        val visited = mutableSetOf<Int>()
        val inspected = mutableSetOf<Int>()

        /**
         * since we start from room 0, add it to visited
         */
        visited.add(0)

        /**
         * start from room 0 and add all the rooms that can be visited from room 0
         */
        val stack = Stack<Int>()
        stack.add(0)

        /**
         * start iterating (consume the stack) until the stack is empty
         */
        while (stack.isNotEmpty()) {
            /**
             * pop the room from the stack
             */
            val currentRoom = stack.pop()

            /**
             * if the room is inspected, skip it (note: use mutableSetOf.add() to add & check if the element is freshly added)
             */
            if (inspected.add(currentRoom).not()) {
                continue
            }

            /**
             * iterate over the rooms that can be visited from the current room
             */
            rooms[currentRoom].forEach { room ->
                if (!visited.contains(room)) {
                    visited.add(room)
                    stack.add(room)
                }

                /**
                 * if all the rooms are visited, return true
                 */
                if (visited.size == rooms.size) {
                    return true
                }
            }
        }

        /**
         * if all the rooms are visited, return true, else false
         */
        return visited.size == rooms.size
    }

    val rooms1 = listOf(listOf(1), listOf(2), listOf(3), listOf())
    println(canVisitAllRooms(rooms1)) // true
    println("---------------")
    val rooms2 = listOf(listOf(1, 3), listOf(3, 0, 1), listOf(2), listOf(0))
    println(canVisitAllRooms(rooms2)) // false
}