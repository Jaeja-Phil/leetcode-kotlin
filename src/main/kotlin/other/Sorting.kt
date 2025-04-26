package other

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun bubbleSort(nums: IntArray): IntArray {
    val last = nums.lastIndex

    for (i in 0..<last) {
        for (j in 0..<last - i) {
            println("i: $i, j: $j, nums: ${nums.joinToString(", ")}")
            if (nums[j] > nums[j + 1]) {
                swap(nums, j, j + 1)
            }
        }
        println("-------------")
    }

    return nums
}

fun selectionSort(nums: IntArray): IntArray {
    val n = nums.lastIndex
    for (i in 0..n) {
        var minIdx = i
        for (j in i + 1..n) {
            if (nums[j] < nums[minIdx]) {
                minIdx = j
            }
        }

        if (minIdx != i) {
            swap(nums, i, minIdx)
        }
    }

    return nums
}

fun insertionSort(nums: IntArray): IntArray {
    val n = nums.lastIndex

    for (i in 1..n) {
        val key = nums[i]
        var j = i - 1

        while (j >= 0 && nums[j] > key) {
            nums[j + 1] = nums[j]
            j--
        }

        nums[j + 1] = key
    }

    return nums
}

fun merge(left: IntArray, right: IntArray): IntArray {
    var l = 0
    var r = 0
    val merged = IntArray(left.size + right.size)
    var i = 0
    while (i < merged.size) {
        merged[i] = when {
            l == left.size -> right[r++]
            r == right.size -> left[l++]
            else -> {
                if (left[l] <= right[r]) {
                    left[l++]
                } else {
                    right[r++]
                }
            }
        }
        i++
    }

    return merged
}

fun mergeSort(nums: IntArray): IntArray {
    if (nums.size == 1) return nums

    val mid = nums.size / 2
    val left = mergeSort(nums.sliceArray(0..<mid))
    val right = mergeSort(nums.sliceArray(mid..nums.lastIndex))

    return merge(left, right)
}

fun quickSort(nums: IntArray): IntArray {
    fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low

        for (j in low..<high) {
            if (arr[j] <= pivot) {
                swap(arr, i++, j)
            }
        }

        swap(arr, i, high)
        return i
    }

    fun sort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivotIdx = partition(arr, low, high)
            sort(arr, low, pivotIdx - 1)
            sort(arr, pivotIdx + 1, high)
        }
    }

    sort(nums, 0, nums.lastIndex)
    return nums
}

fun main() {
    val bubbleSortedArr = bubbleSort(intArrayOf(5, 2, 9, 1, 5, 6))
    println("After sorting: ${bubbleSortedArr.joinToString(", ")}")
    println("-------------")

    val selectionSortedArr = selectionSort(intArrayOf(5, 2, 9, 1, 5, 6))
    println("After sorting: ${selectionSortedArr.joinToString(", ")}")
    println("-------------")

    val insertionSortedArr = insertionSort(intArrayOf(5, 2, 9, 1, 5, 6))
    println("After sorting: ${insertionSortedArr.joinToString(", ")}")
    println("-------------")

    val mergeSortedArr = mergeSort(intArrayOf(5, 2, 9, 1, 5, 6))
    println("After sorting: ${mergeSortedArr.joinToString(", ")}")
    println("-------------")

    val quickSortedArr = quickSort(intArrayOf(5, 2, 9, 1, 5, 6))
    println("After sorting: ${quickSortedArr.joinToString(", ")}")
    println("-------------")
}