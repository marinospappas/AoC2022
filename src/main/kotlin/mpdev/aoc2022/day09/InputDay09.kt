package mpdev.aoc2022.day09

class InputDay09(var trees: List<List<Int>>, var numOfRows: Int, var numOfColumns: Int) {

    private fun getSurroundingList(row: Int, col: Int): List<List<Int>> {
        return listOf(
            mutableListOf<Int>().also { l -> (col-1 downTo 0).forEach { indx -> l.add(trees[row][indx]) } },
            mutableListOf<Int>().also { l -> (col+1 until numOfColumns).forEach { indx -> l.add(trees[row][indx]) } },
            mutableListOf<Int>().also { l -> (row-1 downTo 0).forEach { indx -> l.add(trees[indx][col]) } },
            mutableListOf<Int>().also { l -> (row+1 until numOfRows).forEach { indx -> l.add(trees[indx][col]) } },
        )
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