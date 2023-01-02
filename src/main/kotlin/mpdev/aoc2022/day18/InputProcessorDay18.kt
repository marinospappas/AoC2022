package mpdev.aoc2022.day18

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch
import mpdev.aoc2022.day18.Day18.Point3D

/*
2,2,2
1,2,2
 */
class InputProcessorDay18: InputProcessor<Day18>() {

    override fun process(input: List<String>): Day18 {
        val datalist = mutableListOf<Point3D>()
        input.forEach { line ->
            if (line.matchRegExp(Regex("""^(\d+),(\d+),(\d+)$"""))) {
                val (x, y, z) = regexMatch!!.destructured
                datalist.add(Point3D(x.toInt(), y.toInt(), z.toInt()))
            }
            else
                throw InputDataException("day 18: bad input line: $line")
        }
        return Day18(datalist)
    }
}