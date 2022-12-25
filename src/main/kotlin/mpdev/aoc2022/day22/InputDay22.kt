package mpdev.aoc2022.day22

import mpdev.aoc2022.common.RunTimeException
import mpdev.aoc2022.common.getInput
import java.awt.Point
import java.lang.StringBuilder
import kotlin.math.abs

private const val RIGHT = 0
private const val DOWN = 1
private const val LEFT = 2
private const val UP = 3

class InputDay22(var inputList: List<String>, var path: List<String>) {

    val grid = mutableListOf<MutableList<Char>>()
    private val cubeSize: Int
    private val start: Point
    var position: Position
    val s1TopLeft: Point
    val s1TopRight: Point
    val s2TopLeft: Point
    val s2BotLeft: Point
    val s3TopLeft: Point
    val s3BotLeft: Point
    val s4TopRight: Point
    val s4BotRight: Point
    val s5TopLeft: Point
    val s5BotLeft: Point
    val s6TopLeft: Point
    val s6TopRight: Point
    val s6BotLeft: Point

    init {
        inputList.forEach { grid.add(it.toMutableList()) }
        start = Point(grid[0].start(), 0)
        position = Position(start.x, start.y, RIGHT)
        grid[start.y][start.x] = '>'
        // add padding to short lines so that they match the length
        val width = grid.maxOf { it.size }
        grid.forEach { while(it.size < width) it.add(' ') }
        val height = grid.size
        cubeSize = height / 3
        println("grid width $width, grid height $height, cube size $cubeSize")
//        if (abs(height.toDouble() / 3.0 - width.toDouble() / 4.0) > 0.0001 )
  //          throw RunTimeException("cube size not aligned for dimensions $width x $height")
        // coordinates of the cube sides for part 2
        s1TopLeft = Point(2*cubeSize, 0)
        s1TopRight = Point(3*cubeSize-1, 0)
        s2TopLeft = Point(0, cubeSize)
        s2BotLeft = Point(0, 2*cubeSize-1)
        s3TopLeft = Point(cubeSize, cubeSize)
        s3BotLeft = Point(cubeSize, 2*cubeSize-1)
        s4TopRight = Point(3*cubeSize-1, cubeSize)
        s4BotRight = Point(3*cubeSize-1, 2*cubeSize-1)
        s5TopLeft = Point(2*cubeSize, 2*cubeSize)
        s5BotLeft = Point(2*cubeSize, 3*cubeSize-1)
        s6TopLeft = Point(3*cubeSize, 2*cubeSize)
        s6TopRight = Point(4*cubeSize-1, 2*cubeSize)
        s6BotLeft = Point(3*cubeSize, 3*cubeSize-1)
    }

    fun executeMove(step: String, getNext: () -> Position) {
        if (listOf("L","R").contains(step)) {
            position.changeFacing(step)
            grid[position.y][position.x] = position.toChar()
        }
        else
            for (i in 1..step.toInt()) {
                val next = getNext()
                println("getNext( returned next pos $next for position $position")
                if (canMove(next)) {
                    position.x = next.x
                    position.y = next.y
                    position.facing = next.facing
                    grid[position.y][position.x] = position.toChar()
                }
                else
                    break
            }
    }

    private fun canMove(pos: Position) = grid[pos.y][pos.x] != '#'

    fun nextPosition2D() = when(position.facing) {
        RIGHT -> Position( if(position.x+1 <= grid[position.y].end()) position.x+1 else grid[position.y].start(),
            position.y, position.facing)
        DOWN -> Position(position.x, if(position.y+1 <= grid.end(position.x)) position.y+1
                                        else grid.start(position.x), position.facing)
        LEFT -> Position( if(position.x-1 >= grid[position.y].start()) position.x-1 else grid[position.y].end(),
            position.y, position.facing)
        UP -> Position(position.x, if(position.y-1 >= grid.start(position.x)) position.y-1
                                        else grid.end(position.x), position.facing)
        else -> Position(position.x, position.y, position.facing)
    }

    fun nextPosition3D() = when(position.facing) {
        RIGHT -> {
            if (position.x+1 <= grid[position.y].end())
                Position(position.x+1, position.y, position.facing)
            else
                when (position.y) {
                    in 0 until cubeSize ->
                        Position(s6TopRight.x, s6TopRight.y+cubeSize-position.y-1, LEFT)
                    in cubeSize until 2*cubeSize ->
                        Position(s6TopLeft.x+2*cubeSize-position.y-1, s6TopLeft.y, DOWN)
                    in 2*cubeSize until 3*cubeSize ->
                        Position(s1TopRight.x,  s1TopRight.y+3*cubeSize-position.y-1, LEFT)
                    else -> Position(position.x, position.y, position.facing)
                }
        }
        DOWN -> {
            if (position.y+1 <= grid.end(position.x))
                Position(position.x, position.y+1, position.facing)
            else
                when (position.y) {
                    in 0 until cubeSize ->
                        Position(s5BotLeft.x+cubeSize-position.x-1, s5BotLeft.y, UP)
                    in cubeSize until 2*cubeSize ->
                        Position(s5TopLeft.x, s5TopLeft.y+2*cubeSize-position.x-1, RIGHT)
                    in 2*cubeSize until 3*cubeSize ->
                        Position(s2BotLeft.x+3*cubeSize-position.x-1, s2BotLeft.y, UP)
                    in 3*cubeSize until 4*cubeSize ->
                        Position(s2TopLeft.x, s2TopLeft.y+4*cubeSize-position.x-1, RIGHT)
                    else -> Position(position.x, position.y, position.facing)
                }
        }
        LEFT -> {
            if (position.x-1 >= grid[position.y].start())
                Position(position.x-1, position.y, position.facing)
            else
                when (position.y) {
                    in 0 until cubeSize ->
                        Position(s3TopLeft.x+position.y, s3TopLeft.y, DOWN)
                    in cubeSize until 2*cubeSize ->
                        Position(s6BotLeft.x+2*cubeSize-position.y-1, s6TopLeft.y, UP)
                    in 2*cubeSize until 3*cubeSize ->
                        Position(s3BotLeft.x+2*cubeSize-position.x-1, s3BotLeft.y, UP)
                    else -> Position(position.x, position.y, position.facing)
                }
        }
        UP -> {
            if (position.y-1 >= grid.start(position.x))
                Position(position.x, position.y-1, position.facing)
            else
                when (position.y) {
                    in 0 until cubeSize ->
                        Position(s1TopLeft.x+cubeSize-position.x-1, s1TopLeft.y, DOWN)
                    in cubeSize until 2*cubeSize ->
                        Position(s1TopLeft.x, s1TopLeft.y+2*cubeSize-position.x, RIGHT)
                    in 2*cubeSize until 3*cubeSize ->
                        Position(s2TopLeft.x+3*cubeSize-position.x-1, s2TopLeft.y, DOWN)
                    in 3*cubeSize until 4*cubeSize ->
                        Position(s4TopRight.x, s4TopRight.y+3*cubeSize-position.x-1, LEFT)
                    else -> Position(position.x, position.y, position.facing)
                }
        }
        else -> Position(position.x, position.y, position.facing)
    }

    fun gridToString(): String {
        val s = StringBuilder()
        for (i in grid.indices) {
            s.append("%3d".format(i)).append(": ")
            grid[i].toList().forEach { s.append(it) }
            s.append("\n")
        }
        return s.toString()
    }

    class Position(var x: Int, var y: Int, var facing: Int) {
        fun changeFacing(rotation: String) {
            when (rotation) {
                "L" -> { --facing; if (facing < 0) facing = 3 }
                "R" -> facing = (facing + 1).mod(4)
            }
        }
        fun getPassword() = 1000*(y+1) + 4*(x+1) + facing
        fun toChar() = when(facing) {
            RIGHT -> '>'
            DOWN -> 'v'
            LEFT -> '<'
            UP -> '^'
            else -> ' '
        }

        override fun toString() = "x: $x, y: $y, facing: ${toChar()}"
    }

    fun List<Char>.start() = indexOfFirst { it != ' ' }
    fun List<Char>.end() = indexOfLast { it != ' ' }
    fun List<List<Char>>.end(x: Int): Int {
        for (i in size-1 downTo 0)
            if (this[i][x] != ' ')
                return i
        return 0
    }
    fun List<List<Char>>.start(x: Int): Int {
        for (i in indices)
            if (this[i][x] != ' ')
                return i
        return size-1
    }
}
