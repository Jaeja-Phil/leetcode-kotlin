package medium

import java.util.*

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that
 * the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi
 * respectively. The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Constraints:
 * - 1 <= trips.length <= 1000
 * - trips[i].length == 3
 * - 1 <= numPassengersi <= 100
 * - 0 <= fromi < toi <= 1000
 * - 1 <= capacity <= 100000
 *
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 */
fun main() {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        // first: number of passengers
        // second: start location
        // third: end location
        val waitingPassengers = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.second })
        trips.forEach { waitingPassengers.add(Triple(it[0], it[1], it[2])) }

        val onBoardPassengers = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        var currentCapacity = 0
        while (waitingPassengers.isNotEmpty()) {
            val (numPassengers, startLocation, endLocation) = waitingPassengers.poll()
            while (onBoardPassengers.isNotEmpty() && onBoardPassengers.peek().third <= startLocation) {
                val (passengers, _, _) = onBoardPassengers.poll()
                currentCapacity -= passengers
            }

            currentCapacity += numPassengers
            if (currentCapacity > capacity) {
                return false
            }

            onBoardPassengers.add(Triple(numPassengers, startLocation, endLocation))
        }

        return true
    }

    println(carPooling(arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 3, 7)), 4)) // false
    println(carPooling(arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 3, 7)), 5)) // true
}