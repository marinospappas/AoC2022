package mpdev.aoc2022.day08

class InputDay08(var trees: List<List<Int>>, var numOfRows: Int, var numColumns: Int) {

    fun getSurroundingList(row: Int, col: Int): List<List<Int>> {
        val left = mutableListOf<Int>()
        for (i in 0 until col)
            left.add(trees[row][i])
        val right = mutableListOf<Int>()
        for (i in col+1 until numColumns)
            right.add(trees[row][i])
        val top = mutableListOf<Int>()
        for (i in 0 until row)
            top.add(trees[i][col])
        val bottom = mutableListOf<Int>()
        for (i in row+1 until numOfRows)
            bottom.add(trees[i][col])
        return mutableListOf(left.reversed(), right, top.reversed(), bottom)
    }

    fun isVisible(row: Int, col: Int): Boolean {
        if (row == 0 || row == numOfRows-1 || col == 0 || col == numColumns-1)
            return true
        getSurroundingList(row, col).forEach { if (trees[row][col] > it.max()) return true }
        return false
    }

    fun scenicScore(row: Int, col: Int): Int {
        var score1 = 0
        for (i in col-1 downTo 0) {
            ++score1
            if (trees[row][i] >= trees[row][col])
                break
        }
        var score2 = 0
        for (i in col+1 until numColumns) {
            ++score2
            if (trees[row][i] >= trees[row][col])
                break
        }

        var score3 = 0
        for (i in row-1 downTo 0) {
            ++score3
            if (trees[i][col] >= trees[row][col])
                break
        }
        var score4 = 0
        for (i in row+1 until numOfRows) {
            ++score4
            if (trees[i][col] >= trees[row][col])
                break
        }
        return score1*score2*score3*score4
    }

    override fun toString(): String {
        return trees.toString()
    }

}