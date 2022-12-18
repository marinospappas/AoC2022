package mpdev.aoc2022.day17

import mpdev.aoc2022.utils.xPlus
import mpdev.aoc2022.utils.yPlus
import java.awt.Point
import java.math.BigInteger
import kotlin.math.max
import kotlin.text.StringBuilder

class InputDay17(var movesList: List<Char>) {

    companion object {
        const val PIXEL_FREE = 0
        const val PIXEL_TAKEN = 1
    }
    var grid = mutableListOf<IntArray>()
    var currHeight = -1
    var rockPos = Point(-1,-1)
    var rock: Rock? = null
    var jetPatternIndex = 0

    var rockList = listOf(
        Rock(listOf("@@@@")),
        Rock(listOf(".@.", "@@@", ".@.")),
        Rock(listOf("@@@", "..@", "..@")),
        Rock(listOf("@", "@", "@", "@")),
        Rock(listOf("@@", "@@")),
    )

    var gridState = mutableMapOf<GridState,Pair<Int,Int>>()

    fun playTetris(n: Int): Int {
        (1..n).forEach {
            newRock(rockList[(it - 1) % rockList.size])
            playRock()
        }
        return currHeight + 1
    }

    fun playTetris2(n: String): String {
        val maxCount = BigInteger(n)
        var offset = BigInteger("0")
        var count = BigInteger("1")
        var rockType = 0
        var cycleDetected = false
        while (true) {
            newRock(rockList[rockType])
            playRock()
            if (count++ == maxCount)
                break
            rockType = (++rockType) % rockList.size

            if (!cycleDetected && count.toInt() > 2000) {   // start detecting cycles above 2000 pieces to make sure it has settled
                val state = GridState(grid[currHeight].joinToString(""), rockType, jetPatternIndex)
                val prevCount: Int
                val prevHeight: Int
                if (gridState[state] == null)
                    gridState[state] = Pair(count.toInt(), currHeight)
                else {
                    prevCount = gridState[state]?.first ?: 0
                    prevHeight = gridState[state]?.second ?: 0
                    // cycle detected
                    cycleDetected = true
                    val rockCntCycle = count.toInt() - prevCount
                    val heightCycle = currHeight - prevHeight
                    println(
                        "cycle detected: rock count $prevCount - ${count.toInt()} height ${prevHeight + 1} -  ${currHeight + 1}" +
                                " next rock $rockType next move $jetPatternIndex"
                    )
                    println("cycle: rocks $rockCntCycle height $heightCycle")
                    val cycles = (maxCount - count + BigInteger("1")) / rockCntCycle.toBigInteger()
                    offset += heightCycle.toBigInteger() * cycles
                    count += rockCntCycle.toBigInteger() * cycles
                    println("skipping $cycles cycles to rock count $count height ${(currHeight + 1).toBigInteger() + offset}")
                }
            }
        }
        return ((currHeight + 1).toBigInteger() + offset).toString()
    }

    private fun playRock() {
        while (true) {
            moveRockSideWays(getNextSidewaysMove())
            if (!moveRockDown()) {
                placeRockToRest()
                return
            }
        }
    }

    fun newRock(newRock: Rock) {
        rock = newRock
        (1..(rock!!.height+3)).forEach { grid.add(IntArray(7) { PIXEL_FREE }) }
        rockPos = Point(2, currHeight + 3 + 1)
    }

    fun moveRockDown(): Boolean {
        if (!canMoveDown())
            return false
        rockPos = rockPos.yPlus(-1)
        return true
    }

    fun moveRockSideWays(c: Char): Boolean {
        val xIncr = if (c == '<') -1 else 1
        if (!canMoveSideways(xIncr))
            return false
        rockPos = rockPos.xPlus(xIncr)
        return true
    }

    fun getNextSidewaysMove(): Char {
        val move = movesList[jetPatternIndex++]
        if (jetPatternIndex >= movesList.size)
            jetPatternIndex = 0
        return move
    }

    fun placeRockToRest() {
        rock!!.data.forEach { point -> grid[rockPos.y + point.y][rockPos.x + point.x] = PIXEL_TAKEN }
        currHeight = max(rockPos.y + rock!!.height - 1, currHeight)
        rock = null
    }

    private fun canMoveDown(): Boolean {
        if (rockPos.y == 0)
            return false
        rock!!.data.forEach { point -> if (grid[rockPos.y-1 + point.y][rockPos.x + point.x] == PIXEL_TAKEN) return false }
        return true
    }

    private fun canMoveSideways(xIncr: Int): Boolean {
        if (xIncr == -1 && rockPos.x == 0 || xIncr == 1 && rockPos.x + rock!!.width >= 7)
            return false
        rock!!.data.forEach { point -> if (grid[rockPos.y + point.y][rockPos.x+xIncr + point.x] == PIXEL_TAKEN) return false }
        return true
    }

    fun gridToString() = gridRangeToString(0, grid.size-1)

    fun gridToString(offset: Int) = gridRangeToString(currHeight-offset, currHeight+3)

    private fun gridRangeToString(from: Int, to: Int) =
        StringBuilder().also { s -> (to downTo from).forEach { y ->
            grid[y].indices.forEach { x ->
                s.append(
                    when {
                        grid[y][x] == PIXEL_TAKEN -> "#"
                        rock != null && rock!!.data.contains(Point(x - rockPos.x, y - rockPos.y)) -> "@"
                        else -> "."
                    }
                ) }
            s.append(" :${y+1}").append("\n")
        } }.toString()
}

class Rock(strList: List<String>) {
    var data: MutableList<Point> = mutableListOf()
    val width: Int
    val height: Int
    init {
        strList.indices.forEach { y -> strList[y].indices.forEach {
            x -> if (strList[y][x] != '.') data.add(Point(x,y))
        } }
        width = strList.first().length
        height = strList.size
    }
}

class GridState(val topRow: String, val nextRock: Int, val jetIndx: Int) {
    override fun equals(other: Any?): Boolean {
        return other is GridState && this.topRow == other.topRow && this.nextRock == other.nextRock &&
                this.jetIndx == other.jetIndx
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + topRow.hashCode()
        hash = hash * 31 + nextRock
        hash = hash * 31 + jetIndx
        return hash
    }
    override fun toString() = "state: $topRow, $nextRock, $jetIndx"
}
