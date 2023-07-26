class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        var last = 0;
        nums.forEachIndexed { a, b ->
            nums.forEachIndexed { x, y ->
                if (b == y && a != x) return true
            }
        }
        return false
    }
}
