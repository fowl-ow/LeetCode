
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        if (setOf(s) != setOf(t))
        return s.magic() == t.magic()
    }
}

fun String.magic(): Map<Char, Int> {
    return this.groupingBy { it }.eachCount().toMap()
}
