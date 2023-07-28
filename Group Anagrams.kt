class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        
        
        strs.forEach {
            
        }
    }
        
    fun isAnagram(s: String, t: String): Boolean {
        val sm = s.groupingByCount().toSortedMap()
        val tm = t.groupingByCount().toSortedMap()
        return sm == tm
    }
}

fun String.groupingByCount(): Map<Char, Int> {
    return this.groupingBy { it }.eachCount()
}

