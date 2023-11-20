package medium

import java.util.Stack

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * constraints:
 * - 2 <= asteroids.length <= 10^4
 * - -1000 <= asteroids[i] <= 1000
 * - asteroids[i] != 0
 *
 * Example 1:
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 */
fun main() {
    val res1 = `Asteroid Collision`(intArrayOf(5, 10, -5))
    println(res1.contentToString()) // [5, 10]

    val res2 = `Asteroid Collision`(intArrayOf(8, -8))
    println(res2.contentToString()) // []

    val res3 = `Asteroid Collision`(intArrayOf(10, 2, -5))
    println(res3.contentToString()) // [10]
}

fun `Asteroid Collision`(asteroids: IntArray): IntArray {
    /**
     * create a stack to keep track of the asteroids.
     */
    val s = Stack<Int>()

    /**
     * iterate through the asteroids
     */
    for (i in 0..asteroids.lastIndex) {
        /**
         * if the stack is not empty (there is asteroid/s to collide with) and current asteroid is negative (moving left),
         * check whether the top of the stack (last asteroid) is positive (moving right)
         * - in essence, check if there is going to be a collision
         */
        while (s.isNotEmpty() && asteroids[i] < 0 && s.peek() > 0) {
            /**
             * check the difference,
             * - if the difference is negative, it means the current asteroid is smaller than the last asteroid
             *   - pop (destroy) the last asteroid
             * - if the difference is positive, it means the current asteroid is bigger than the last asteroid
             *   - destroy the current asteroid by setting it to 0
             * - if the two asteroids are of the same size, destroy both by
             *   - setting the current asteroid to 0
             *   - pop (destroy) the last asteroid
             */
            val diff = asteroids[i] + s.peek()
            when {
                diff < 0 -> s.pop()
                diff > 0 -> asteroids[i] = 0
                else -> { asteroids[i] = 0; s.pop() }
            }
        }

        /**
         * once the collisions are all taken care of, push the current asteroid to the stack
         * unless it is destroyed (set to 0)
         */
        if (asteroids[i] != 0) {
            s.push(asteroids[i])
        }
    }

    return s.toIntArray()
}