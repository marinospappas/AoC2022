package mpdev.aoc2022.day08

class InputDay08(var trees: List<List<Int>>, var numOfRows: Int, var numOfColumns: Int) {

    private fun getSurroundingList(row: Int, col: Int): List<List<Int>> {
        val left = mutableListOf<Int>()
        for (i in col-1 downTo 0)
            left.add(trees[row][i])
        val right = mutableListOf<Int>()
        for (i in col+1 until numOfColumns)
            right.add(trees[row][i])
        val top = mutableListOf<Int>()
        for (i in row-1 downTo 0)
            top.add(trees[i][col])
        val bottom = mutableListOf<Int>()
        for (i in row+1 until numOfRows)
            bottom.add(trees[i][col])
        return listOf(left, right, top, bottom)
    }

    fun isVisible(row: Int, col: Int): Boolean {
        if (row == 0 || row == numOfRows-1 || col == 0 || col == numOfColumns-1)
            return true
        getSurroundingList(row, col).forEach { if (trees[row][col] > it.max()) return true }
        return false
    }

    fun scenicScore(row: Int, col: Int): Int {
        val scoreList = mutableListOf(0,0,0,0)
        val surroundings = getSurroundingList(row, col)
        for(i in surroundings.indices) run outerLoop@ {
            surroundings[i].forEach { ++scoreList[i]; if (it >= trees[row][col]) return@outerLoop }
        }
        return scoreList.reduce { acc, item -> acc * item }
    }

    override fun toString(): String {
        return trees.toString()
    }

}