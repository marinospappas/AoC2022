package mpdev.aoc2022.day18

import java.util.*

const val AIR = 0
const val WATER = 1
const val LAVA = 2

class Day18(var pointsList: List<Point3D>) {

    val maxX = pointsList.maxOf { it.x }
    val maxY = pointsList.maxOf { it.y }
    val maxZ = pointsList.maxOf { it.z }
    val arrSize = maxOf(maxX, maxY, maxZ) + 1
    val cube: Array<Array<IntArray>> =
        Array(arrSize) { Array(arrSize) { IntArray(arrSize){ AIR } } }

    init {
        pointsList.forEach { cube[it.z][it.y][it.x] = LAVA }
    }

    fun getExposedSurface(points: List<Point3D>): Int {
        var exposed = 0
        points.forEach { point ->
            point.getAdjacent().forEach { neighbour -> if (!points.contains(neighbour)) ++exposed }
        }
        return exposed
    }

    fun getOutsideExposedSurface() = getExposedSurface(pointsList) - getExposedSurface(findAirPockets())

    private fun findAirPockets(): List<Point3D> {
        val stack: Deque<Point3D> = ArrayDeque()
        val visited: MutableSet<Point3D> = HashSet()
        stack.push(Point3D(0,0,0))
        while (!stack.isEmpty()) {
            val cur = stack.pop()
            visited.add(cur)
            if (cube[cur.x][cur.y][cur.z] == AIR) {
                cube[cur.x][cur.y][cur.z] = WATER
                for (neighbour in cur.getAdjacent())
                    if (!visited.contains(neighbour) && withinCube(neighbour))
                        stack.push(neighbour)
            }
        }
        return getAirPockets()
    }

    private fun getAirPockets(): List<Point3D> {
        val airPockets = mutableListOf<Point3D>()
        (0 until cube.size).forEach { z -> (0 until cube[0].size).forEach { y-> (0 until cube[0][0].size).forEach { x ->
            if (cube[z][y][x] == AIR)
                airPockets.add(Point3D(x,y,z))
        } } }
        println("air pockets: ${airPockets.size}")
        return airPockets
    }

    private fun withinCube(p: Point3D) =
        p.x >= 0 && p.y >= 0 && p.z >= 0 && p.x < cube.size && p.y < cube[0].size && p.z < cube[0][0].size

    data class Point3D(val x: Int, val y: Int, val z: Int) {
        fun getAdjacent() = mutableListOf<Point3D>().also { l -> listOf(
            Point3D(0, 1, 0), Point3D(0, -1, 0), Point3D(1, 0, 0),
            Point3D(-1, 0, 0), Point3D(0, 0, 1), Point3D(0, 0, -1)
        ).forEach { l.add(this + it) }
        }
        operator fun plus(other: Point3D) =
            Point3D(this.x + other.x, this.y + other.y, this.z + other.z)
    }
}
