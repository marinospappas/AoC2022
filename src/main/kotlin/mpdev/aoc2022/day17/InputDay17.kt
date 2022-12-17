package mpdev.aoc2022.day17

import java.awt.Point
import java.lang.RuntimeException
import java.lang.StringBuilder

class InputDay17(var movesList: List<Char>) {

    var grid = mutableListOf<CharArray>()
    var dimensions = Pair(7, Int.MAX_VALUE)
    var currHeight = -1
    var rockX = 0
    var rockY = 0
    lateinit var rock: Rock
    var jetPatternIndex = 0

    var rockList = listOf(
        listOf(
            "@@@@".toCharArray()
        ),
        listOf(
            ".@.".toCharArray(),
            "@@@".toCharArray(),
            ".@.".toCharArray()
        ),
        listOf(
            "@@@".toCharArray(),
            "..@".toCharArray(),
            "..@".toCharArray()
        ),
        listOf(
            "@".toCharArray(),
            "@".toCharArray(),
            "@".toCharArray(),
            "@".toCharArray()
        ),
        listOf(
            "@@".toCharArray(),
            "@@".toCharArray()
        ),
    )

    fun playTetris(n: Int): Int {
        (1..n).forEach {
            newRock(Rock(rockList[(it - 1) % rockList.size]))
            playRock()
        }
        return currHeight + 1
    }

    fun playRock() {
        while (true) {
            when (getNextSidewaysMove()) {
                '<' -> moveRockLeft()
                '>' -> moveRockRight()
                else -> throw RuntimeException("invalid move character")
            }
            if (!moveRockDown()) {
                setRockToStopped()
                return
            }
        }
    }

    fun extendGrid(n: Int) {
        for (i in 1..n) {
            val newRow = CharArray(7) { '.' }
            grid.add(newRow)
        }
    }

    fun newRock(newRock: Rock) {
        rock = Rock(newRock.data)
        extendGrid(rock.height + 3)
        placeRock(2, currHeight + 3 + 1)
    }

    fun moveRockDown(): Boolean {
        if (!canMoveDown())
            return false
        removeRock()
        placeRock(rockX, rockY - 1)
        return true
    }

    fun moveRockLeft(): Boolean {
        if (!canMoveLeft())
            return false
        removeRock()
        placeRock(rockX - 1, rockY)
        return true
    }

    fun moveRockRight(): Boolean {
        if (!canMoveRight())
            return false
        removeRock()
        placeRock(rockX + 1, rockY)
        return true
    }

    fun getNextSidewaysMove(): Char {
        val move = movesList[jetPatternIndex++]
        if (jetPatternIndex >= movesList.size)
            jetPatternIndex = 0
        return move
    }

    fun setRockToStopped() {
        for (h in 0 until rock.height)
            for (w in 0 until rock.width)
                if (grid[rockY + h][rockX + w] == '@')
                    grid[rockY + h][rockX + w] = '#'
        if (rockY + rock.height - 1 > currHeight)
            currHeight = rockY + rock.height - 1
    }

    private fun placeRock(x: Int, y: Int) {
        for (h in 0 until rock.height)
            for (w in 0 until rock.width)
                if (rock[h][w] != '.')
                    grid[y + h][x + w] = rock[h][w]
        rockX = x
        rockY = y
    }

    private fun removeRock() {
        for (h in 0 until rock.height)
            for (w in 0 until rock.width)
                if (rock[h][w] != '.')
                    grid[rockY + h][rockX + w] = '.'
    }

    private fun canMoveDown(): Boolean {
        if (rockY == 0)
            return false
        rock.getListOfSolidPoints().forEach {
            if (grid[rockY+it.y-1][rockX+it.x] == '#')
                return false
        }
        return true
    }

    private fun canMoveLeft(): Boolean {
        if (rockX == 0)
            return false
        rock.getListOfSolidPoints().forEach {
            if (grid[rockY+it.y][rockX+it.x-1] == '#')
                return false
        }
        return true
    }

    private fun canMoveRight(): Boolean {
        if (rockX + rock.width >= 7)
            return false
        rock.getListOfSolidPoints().forEach {
            if (grid[rockY+it.y][rockX+it.x+1] == '#')
                return false
        }
        return true
    }

    fun gridToString(offset: Int): String {
        val s = StringBuilder()
        for (i in currHeight+3 downTo currHeight-offset) {
            grid[i].toList().forEach { s.append(it) }
            s.append(" :${i+1}").append("\n")
        }
        return s.toString()
    }

    fun gridToString(): String {
        val s = StringBuilder()
        for (i in grid.size-1 downTo 0) {
            grid[i].toList().forEach { s.append(it) }
            s.append(" :${i+1}").append("\n")
        }
        return s.toString()
    }
}

class Rock(var data: List<CharArray>) {
    val width = data[0].size
    val height = data.size
    operator fun get(row: Int) = data[row]

    fun getListOfSolidPoints(): List<Point> =
        mutableListOf<Point>().also { list ->
            (0 until height).forEach { row -> (0 until width).forEach { col ->
                if (data[row][col] != '.') list.add(Point(col, row))
            } }
        }
}

