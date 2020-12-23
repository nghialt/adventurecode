package main.kotlin

fun isNumberCorrect(num: Int, listRanges: List<Pair<Int, Int>>): Boolean {
    val isExisted = listRanges.firstOrNull { pair -> num >= pair.first && num <= pair.second }
    return isExisted != null
}
