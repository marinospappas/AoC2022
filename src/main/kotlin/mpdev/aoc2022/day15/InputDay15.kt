package mpdev.aoc2022.day15

import mpdev.aoc2022.utils.append
import mpdev.aoc2022.utils.manhattanDist
import java.awt.Point
import kotlin.math.abs

class InputDay15(var inputList: List<Sensor>) {

    var grid = Grid(inputList)

}

class Sensor(var pos: Point, var bcn: Point) {
    var bcnDist = 0

    fun getPointsOfNoBcnForY(y: Int) : IntRange? {
        return if (abs(y - pos.y) > bcnDist)
            null
        else
            (pos.x - bcnDist + abs(y - pos.y))..(pos.x + bcnDist - abs(y - pos.y))
    }

    override fun toString() =
        "sensor x=${pos.x}, y=${pos.y}, cl.beacon x:${bcn.x}, ${bcn.y}, bcndist: $bcnDist"
}

class Grid(var sensorList: List<Sensor>) {

    val beaconList = mutableListOf<Point>()

    var pointOfDistressBeacon = Point(Int.MIN_VALUE, Int.MIN_VALUE)
    init {
        sensorList.forEach { s ->
            s.bcnDist = manhattanDist(s.pos, s.bcn)
            beaconList.add(s.bcn)
        }
    }

    fun getNoBcnRangeForY(y: Int): List<IntRange> {
        pointOfDistressBeacon = Point(Int.MIN_VALUE,y)
        val noBcnList = mutableListOf<IntRange>()
        sensorList.forEach { it.getPointsOfNoBcnForY(y).also { range -> if (range != null) noBcnList.add(range) } }
        return noBcnList.sortedBy { it.first }
    }

    fun consolidateRanges(listRanges: List<IntRange>): IntRange? {
        if (listRanges.isEmpty())
            return null
        var result: IntRange? = listRanges.first()
        for (i in 1 until listRanges.size) {
            val previous = result
            result = result!!.append(listRanges[i])
            if (result == null) {
                pointOfDistressBeacon = Point(listRanges[i].first-1,pointOfDistressBeacon.y)
                return null
            }
        }
        return result
    }
}

