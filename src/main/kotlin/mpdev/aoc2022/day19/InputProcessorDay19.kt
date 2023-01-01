package mpdev.aoc2022.day19

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay19: InputProcessor<Day19>() {
    // Blueprint 1: Each ore robot costs 4 ore.
    //   Each clay robot costs 2 ore.
    //   Each obsidian robot costs 3 ore and 14 clay.
    //   Each geode robot costs 2 ore and 7 obsidian.
    override fun process(input: List<String>): Day19 {
        val bluePrintList = mutableListOf<BluePrint>()
        input.forEach { line ->
            if (line.matchRegExp(Regex("""^Blueprint (\d+): Each ore robot costs (\d+) ore. Each clay robot costs (\d+) ore. """+
                """Each obsidian robot costs (\d+) ore and (\d+) clay. Each geode robot costs (\d+) ore and (\d+) obsidian.$"""))) {
                val (id, orOcost, crOcost, obrOcost, obrCcost, grOcost, grObcost) = regexMatch!!.destructured
                bluePrintList.add(BluePrint(
                    id.toInt(),
                    OreRobot(orOcost.toInt()),
                    ClayRobot(crOcost.toInt()),
                    ObsidianRobot(obrOcost.toInt(), obrCcost.toInt()),
                    GeodeRobot(grOcost.toInt(), grObcost.toInt())
                ))
            }
            else
                throw InputDataException("day 19 bad input line: $line")
        }
        return Day19(bluePrintList)
    }

}