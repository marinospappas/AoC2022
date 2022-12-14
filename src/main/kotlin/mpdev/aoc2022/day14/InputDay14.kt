package mpdev.aoc2022.day14

import java.lang.StringBuilder
import kotlin.math.max
import kotlin.math.min

class InputDay14(var inputList: List<List<Pair<Int,Int>>>) {

    val START = Pair(500,0)

    //var minPt: Pair<Int,Int>
    //var maxPt: Pair<Int,Int>
    var grid: Array<CharArray>
    var dimensions: Pair<Int,Int>
    var shift: Int

    init {
        val minx = inputList.minOf { l -> l.minOf { it.first } }
        //minPt = Pair(minx, 0)
        val maxx = inputList.maxOf { l -> l.maxOf { it.first } }
        val maxy = inputList.maxOf { l -> l.maxOf { it.second } }
        //maxPt = Pair(maxx, maxy)
        dimensions = Pair(maxx-minx+1 /*+4000*/, maxy+1)
        shift = -minx /* + 2000 */
        grid =  Array(dimensions.second) { CharArray(dimensions.first) { '.' } }
        grid[0][500+shift] = '+'
        inputList.forEach { list ->
            for (i in 0 .. list.lastIndex-1)
                drawLine(list[i], list[i+1])
        }
    }

    fun extendGrid2() {
        val oldGrid = grid
        dimensions = Pair(dimensions.first, dimensions.second+2)
        grid = Array(dimensions.second) { CharArray(dimensions.first) }
        for (i in oldGrid.indices)
            grid[i] = oldGrid[i]
        grid[dimensions.second-2] = CharArray(dimensions.first) { '.' }
        grid[dimensions.second-1] = CharArray(dimensions.first) { '#' }
    }

    private fun extendGridLeft() {
        val oldGrid = grid
        dimensions = Pair(dimensions.first+1, dimensions.second)
        grid = Array(dimensions.second) { CharArray(dimensions.first) }
        for (i in oldGrid.indices)
            grid[i] = (listOf('.') + oldGrid[i].toList()).toCharArray()
        grid[dimensions.second-1][0] = '#'
        ++shift
    }

    private fun extendGridRight() {
        val oldGrid = grid
        dimensions = Pair(dimensions.first+1, dimensions.second)
        grid = Array(dimensions.second) { CharArray(dimensions.first) }
        for (i in oldGrid.indices)
            grid[i] = (oldGrid[i].toList() + listOf('.')).toCharArray()
        grid[dimensions.second-1][dimensions.first-1] = '#'
    }

    fun dropOneGrain(): Boolean {
        var from = START
        var to: Pair<Int,Int>
        while (moveGrainDown(from).also { to = it }.first > -1)
            from = to
        if (to.first < -1) {  // can't rest anywhere
            println("move from $from not possible")
            return false
        }
        grid[from.second][from.first+shift] = 'o'
        return true
    }

    fun dropOneGrain2(): Boolean {
        if (grid[0][500+shift] == 'o') {
            return false
        }
        var from = START
        var to: Pair<Int,Int>
        while (moveGrainDown2(from).also { to = it }.first > -1) {
            from = to
        }
        if (to.first < -1) { // can't fit
            println("move from $from not possible")
            return false
        }
        grid[from.second][from.first+shift] = 'o'
        return true
    }

    fun getGrainsCount(): Int {
        var res = 0
        for (i in grid.indices)
            res += grid[i].toList().filter { it == 'o' }.count()
        return res
    }

    private fun moveGrainDown(fromPoint: Pair<Int,Int>): Pair<Int,Int> {
        // attempt to move down
        if (fromPoint.second+1 >= dimensions.second)
            return Pair(Int.MIN_VALUE, Int.MIN_VALUE)
        if (grid[fromPoint.second+1][fromPoint.first+shift] == '.')
            return Pair(fromPoint.first, fromPoint.second+1)
        // attempt to move diagonally left
        if (fromPoint.second+1 > dimensions.second || fromPoint.first-1+shift < 0)
            return Pair(Int.MIN_VALUE, Int.MIN_VALUE)
        if (grid[fromPoint.second+1][fromPoint.first-1+shift] == '.')
            return Pair(fromPoint.first-1, fromPoint.second+1)
        // attempt to move diagonally right
        if (fromPoint.second+1 > dimensions.second || fromPoint.first+1+shift > dimensions.first)
            return Pair(Int.MIN_VALUE, Int.MIN_VALUE)
        if (grid[fromPoint.second+1][fromPoint.first+1+shift] == '.')
            return Pair(fromPoint.first+1, fromPoint.second+1)

        return Pair(-1,-1)
    }

    private fun moveGrainDown2(fromPoint: Pair<Int,Int>): Pair<Int,Int> {
        // attempt to move down
        if (fromPoint.second+1 >= dimensions.second)
            return Pair(Int.MIN_VALUE, Int.MIN_VALUE)
        if (grid[fromPoint.second+1][fromPoint.first+shift] == '.')
            return Pair(fromPoint.first, fromPoint.second+1)
        // attempt to move diagonally left
        if (fromPoint.second+1 > dimensions.second || fromPoint.first-1+shift < 0)
            extendGridLeft()
        if (grid[fromPoint.second+1][fromPoint.first-1+shift] == '.')
            return Pair(fromPoint.first-1, fromPoint.second+1)
        // attempt to move diagonally right
        if (fromPoint.second+1 > dimensions.second || fromPoint.first+1+shift > dimensions.first-1)
            extendGridRight()
        if (grid[fromPoint.second+1][fromPoint.first+1+shift] == '.')
            return Pair(fromPoint.first+1, fromPoint.second+1)

        return Pair(-1,-1)
    }

    private fun drawLine(p1: Pair<Int,Int>, p2: Pair<Int,Int>) {
        if (p1.second == p2.second)
            drawHorLine(p1, p2)
        else
        if (p1.first == p2.first)
            drawVerLine(p1, p2)
    }

    private fun drawHorLine(p1: Pair<Int,Int>, p2: Pair<Int,Int>) {
        for (i in min(p1.first,p2.first) .. max(p1.first,p2.first))
            grid[p1.second][i+shift] = '#'
    }

    private fun drawVerLine(p1: Pair<Int,Int>, p2: Pair<Int,Int>) {
        for (i in min(p1.second,p2.second) .. max(p1.second,p2.second))
            grid[i][p1.first+shift] = '#'
    }

    fun gridToString(): String {
        val s = StringBuilder()
        for (i in grid.indices) {
            grid[i].toList().forEach { s.append(it) }
            s.append("\n")
        }
        return s.toString()
    }
}
