package mpdev.aoc2022.day22

import java.awt.Point
import java.lang.StringBuilder

private const val RIGHT = 0
private const val DOWN = 1
private const val LEFT = 2
private const val UP = 3

class InputDay22(var inputList: List<String>, var path: List<String>) {

    val grid = mutableListOf<MutableList<Char>>()
    private val start: Point
    var position: Position

    init {
        inputList.forEach { grid.add(it.toMutableList()) }
        start = Point(grid[0].start(), 0)
        position = Position(start.x, start.y, RIGHT)
        grid[start.y][start.x] = '>'
        // add padding to short lines so that they match the length
        val width = grid.maxOf { it.size }
        grid.forEach { while(it.size < width) it.add(' ') }
    }

    fun executeMove(step: String) {
        when (step) {
            "L", "R" -> {
                position.changeFacing(step)
                grid[position.y][position.x] = position.toChar()
            }
            else -> for (i in 1..step.toInt()) {
                val next = nextPosition()
                if (canMove(next)) {
                    position.x = next.x
                    position.y = next.y
                    grid[position.y][position.x] = position.toChar()
                }
                else
                    break
            }
        }
    }

    private fun canMove(pos: Position) = grid[pos.y][pos.x] != '#'

    private fun nextPosition() = when(position.facing) {
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
