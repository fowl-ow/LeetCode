class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val arr = nums.sorted()

        var left = 0
        var right = arr.size - 1

        while (left < right) {
            val sum = arr[left] + arr[right]

            when {
                sum == target -> {
                    val ai = nums.indexOf(arr[left])
                    val bi = nums.lastIndexOf(arr[right])
                    return intArrayOf(ai, bi)
                }
                sum < target -> left++
                else -> right--
            }
        }

        return intArrayOf()
    }
}
