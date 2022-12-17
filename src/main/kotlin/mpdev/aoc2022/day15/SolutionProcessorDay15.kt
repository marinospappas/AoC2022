package mpdev.aoc2022.day15

import mpdev.aoc2022.common.*

class SolutionProcessorDay15: SolutionProcessor<InputDay15> {

    var inputPart1 = 2000000
    var inputPart2 = 4000000

    /** part 1 calculation */
    override fun part1(input: InputDay15): String {
        val consRange = input.grid.consolidateRanges(input.grid.getNoBcnRangeForY(inputPart1))
        if (consRange != null) {
            val beaconsOnThisY = input.grid.beaconList.distinct().count { it.y == inputPart1 }
            return (consRange.last - consRange.first + 1 - beaconsOnThisY).toString()
        }
        else return "Solution not Found"
    }


    override fun part2(input: InputDay15): String {
        for (y in 0..inputPart2) {
            val ranges = input.grid.getNoBcnRangeForY(y)
            val consRange = input.grid.consolidateRanges(ranges)
            if (consRange == null) {
                val res = input.grid.pointOfDistressBeacon
                println(res)
                val res1 = res.x * 4 + res.y / 1000000
                val res2 = res.y % 1000000
                val resStr = res1.toString() + "%06d".format(res2)
                return resStr
            }
        }
        return "Solution not Found"
    }
}