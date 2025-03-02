package medium

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
 * prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take
 * course ai.
 * - For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Constraints:
 * - 1 <= numCourses <= 2000
 * - 0 <= prerequisites.length <= 5000
 * - prerequisites[i].length == 2
 * - 0 <= ai, bi < numCourses
 * - All the pairs prerequisites[i] are unique.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is
 * possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take
 * course 0 you should also have finished course 1. So it is impossible.
 */
fun main() {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // Map each course to its prerequisites
        val preReqMap = HashMap<Int, MutableSet<Int>>()
        for (i in 0..<numCourses) {
            preReqMap[i] = mutableSetOf()
        }
        for (item in prerequisites) {
            val (course, preReq) = item
            preReqMap[course]!!.add(preReq)
        }

        fun dfs(
            course: Int,
            visiting: MutableSet<Int> = mutableSetOf(),
        ): Boolean {
            // Cycle detected
            if (course in visiting) {
                return false
            }
            // if there are no prerequisites for this course, we can take it. Return true
            if (preReqMap[course]!!.isEmpty()) {
                return true
            }

            // Mark the course as visiting
            visiting.add(course)

            // iterate through the prerequisites of the course and check if we can take them
            val preReqs = preReqMap[course]!!
            for (pre in preReqs) {
                if (!dfs(pre, visiting)) {
                    return false
                }
            }

            // Backtrack and remove the course from the visiting set
            visiting.remove(course)

            // if we can take all the prerequisites of the course (false was not returned), we can take the course
            // empty the prerequisites of the course
            preReqMap[course] = mutableSetOf()

            // finally, return true
            return true
        }

        for (c in 0..<numCourses) {
            if (!dfs(c)) {
                return false
            }
        }
        return true
    }

    println(canFinish(2, arrayOf(intArrayOf(1, 0)))) // true
    println(canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1)))) // false
}
