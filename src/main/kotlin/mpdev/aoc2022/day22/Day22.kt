package mpdev.aoc2022.day22

import java.awt.Point
import java.lang.StringBuilder

const val RIGHT = 0
const val DOWN = 1
const val LEFT = 2
const val UP = 3

class Day22(var inputList: List<String>, var path: List<String>) {

    val grid = mutableListOf<MutableList<Char>>()
    private val cubeSize: Int
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
        val height = grid.size
        cubeSize = if (height < width) height / 3 else height / 4
        println("grid width $width, grid height $height, cube size $cubeSize")
        // initialise coordinates of the cube edges for part 2
        Transformation3D.initTransformations()
    }

    fun executeMove(step: String, getNext: () -> Position) {
        if (listOf("L","R").contains(step)) {
            position.changeFacing(step)
            grid[position.y][position.x] = position.toChar()
        }
        else
            for (i in 1..step.toInt()) {
                val next = getNext()
                //println("getNext( returned next pos $next for position $position")
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
        RIGHT -> if (position.x+1 <= grid[position.y].end()) Position(position.x+1, position.y, position.facing)
                    else Transformation3D.getNextPosition3D(position)
        DOWN -> if (position.y+1 <= grid.end(position.x)) Position(position.x, position.y+1, position.facing)
                    else Transformation3D.getNextPosition3D(position)
        LEFT -> if (position.x-1 >= grid[position.y].start()) Position(position.x-1, position.y, position.facing)
                    else Transformation3D.getNextPosition3D(position)
        UP -> if(position.y-1 >= grid.start(position.x)) Position(position.x, position.y-1, position.facing)
                    else Transformation3D.getNextPosition3D(position)
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

    data class Position(var x: Int, var y: Int, var facing: Int) {
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
