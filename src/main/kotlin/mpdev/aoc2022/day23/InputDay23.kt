package mpdev.aoc2022.day23

import java.awt.Point
import java.lang.StringBuilder
import mpdev.aoc2022.utils.plus

class InputDay23(var inputList: List<String>) {

    private val START = Pair(500,0)

    companion object {
        lateinit var grid: Array<CharArray>
        val movesList = listOf(
            listOf(Point(-1,-1), Point(0,-1), Point(+1,-1)),     // NW, N, NE
            listOf(Point(-1,+1), Point(0,+1), Point(+1,+1)),     // SW, S, SE
            listOf(Point(-1,-1), Point(-1,0), Point(-1,+1)),     // NW, W, SW
            listOf(Point(+1,-1), Point(+1,0), Point(+1,+1)),     // NE, E, SE
        )
    }
    val elvesList = mutableListOf<Elf>()
    var dimensions: Point
    var movesIndx = 0

    init {
        dimensions = Point(inputList.first().length, inputList.size)
        grid =  Array(dimensions.y) { inputList[it].toCharArray() }
        (0 until dimensions.y).forEach { y ->
            (0 until dimensions.x).forEach { x -> if (grid[y][x] == '#') elvesList.add(Elf(Point(x, y))) }
        }
        // extend grid if needed
        extendGrid()
    }

    fun playRound() {
        // decide moves
        val thisRoundMoveIndex = movesIndx++ % movesList.size
        elvesList.forEach { elf ->
            for (i in movesList.indices) {
                val indx = (thisRoundMoveIndex + i) % movesList.size
                if (elf.canMove(movesList[indx])) {
                    elf.makeDecision(movesList[indx][1])
                    break
                }
            }
        }
        // execute moves
        elvesList.forEach { elf ->
            if (elvesList.filter { it.newPos.equals(elf.newPos) }.size == 1)
                elf.moveToNewPos()
        }
        // extend grid if needed
        extendGrid()
    }

    fun getResult() =
        grid.toList().sumOf { row -> row.count { it == '.' } } -
                2*dimensions.x - 2*(dimensions.y-2)

    fun extendGrid() {
        if (grid.first().count { it == '#' } > 0)
            extendGridUp()
        if (grid.last().count { it == '#' } > 0)
            extendGridDown()
        if (grid.filter { it.first() == '#' }.isNotEmpty() )
            extendGridLeft()
        if (grid.filter { it.last() == '#' }.isNotEmpty() )
            extendGridRight()
    }

    fun extendGridUp() {
        val oldGrid = grid
        dimensions = Point(dimensions.x, dimensions.y+1)
        grid = Array(dimensions.y) { CharArray(dimensions.x) }
        grid[0] = CharArray(dimensions.x) { '.' }
        for (i in oldGrid.indices)
            grid[i+1] = oldGrid[i]
        // adjust the elves positions
        elvesList.forEach { it.pos = Point(it.pos.x, it.pos.y+1) }
    }

    fun extendGridDown() {
        val oldGrid = grid
        dimensions = Point(dimensions.x, dimensions.y+1)
        grid = Array(dimensions.y) { CharArray(dimensions.x) }
        for (i in oldGrid.indices)
            grid[i] = oldGrid[i]
        grid[dimensions.y-1] = CharArray(dimensions.x) { '.' }
    }

    private fun extendGridLeft() {
        val oldGrid = grid
        dimensions = Point(dimensions.x+1, dimensions.y)
        grid = Array(dimensions.y) { CharArray(dimensions.x) }
        for (i in oldGrid.indices)
            grid[i] = (listOf('.') + oldGrid[i].toList()).toCharArray()
        // adjust the elves positions
        elvesList.forEach { it.pos = Point(it.pos.x+1, it.pos.y) }
    }

    private fun extendGridRight() {
        val oldGrid = grid
        dimensions = Point(dimensions.x+1, dimensions.y)
        grid = Array(dimensions.y) { CharArray(dimensions.x) }
        for (i in oldGrid.indices)
            grid[i] = (oldGrid[i].toList() + listOf('.')).toCharArray()
    }

    fun gridToString(): String {
        val s = StringBuilder()
        for (i in grid.indices) {
            grid[i].toList().forEach { s.append(it) }
            s.append("\n")
        }
        return s.toString()
    }

    class Elf(var pos: Point) {
        lateinit  var newPos: Point
        fun canMove(destList: List<Point>): Boolean {
            newPos = Point(pos.x, pos.y)
            return destList.filter { grid[(pos + it).y][(pos + it).x] == '#' }.isEmpty() &&
                    movesList.flatten().filter { grid[(pos + it).y][(pos + it).x] == '#' }.isNotEmpty()
        }
        fun makeDecision(moveBy: Point) {
            newPos = pos + moveBy
        }
        fun moveToNewPos() {
            grid.setItem(pos, '.')
            pos = Point(newPos.x, newPos.y)
            grid.setItem(pos, '#')
        }
    }

}

fun Array<CharArray>.setItem(pos: Point, value: Char) {
    this[pos.y][pos.x] = value
}
fun Array<CharArray>.getItem(pos: Point) = this[pos.y][pos.x]