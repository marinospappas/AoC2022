package mpdev.aoc2022.day18

import java.util.*

const val AIR = 0
const val WATER = 1
const val LAVA = 2

class Day18(var pointsList: List<Point3D>) {

    private val airPockets = mutableListOf<Point3D>()
    private val arrSize = maxOf(pointsList.maxOf { it.x }, pointsList.maxOf { it.y }, pointsList.maxOf { it.z }) + 1
    private val cube: Array<Array<IntArray>> = Array(arrSize) { Array(arrSize) { IntArray(arrSize){ AIR } } }

    init {
        pointsList.forEach { cube[it.z][it.y][it.x] = LAVA }
        floodCube()
        cube.indices.forEach { z ->
            (0 until cube[0].size).forEach { y ->
                (0 until cube[0][0].size).forEach { x ->
                    if (cube[z][y][x] == AIR)
                        airPockets.add(Point3D(x, y, z))
                }
            }
        }
    }

    fun getExposedSurface(onlyOuterSurface: Boolean = false): Int {
        var exposed = 0
        pointsList.forEach { point ->
            point.getAdjacent().forEach { neighbour ->
                if (!pointsList.contains(neighbour) && (!onlyOuterSurface || !airPockets.contains(neighbour)))
                    ++exposed
            }
        }
        return exposed
    }

    private fun floodCube() {
        val queue: Queue<Point3D> = LinkedList()
        val visited: MutableSet<Point3D> = HashSet()
        queue.add(Point3D(0,0,0))
        while (!queue.isEmpty()) {
            val current = queue.poll()
            visited.add(current)
            if (cube[current.x][current.y][current.z] == AIR) {
                cube[current.x][current.y][current.z] = WATER
                for (neighbour in current.getAdjacent())
                    if (!visited.contains(neighbour) && insideCube(neighbour))
                        queue.add(neighbour)
            }
        }
    }

    private fun insideCube(p: Point3D) =
        p.z in cube.indices && p.y in 0 until cube[0].size && p.x in 0 until cube[0][0].size

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
