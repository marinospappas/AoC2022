package mpdev.aoc2022.day15

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch
import java.awt.Point

/*
Sensor at x=2, y=18: closest beacon is at x=-2, y=15
 */
class InputProcessorDay15: InputProcessor<InputDay15>() {

    private fun processLine(line: String, datalist: MutableList<Sensor>) {
        when {
            line.matchRegExp(Regex("""^Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)$""")) -> {
                val (xs, ys, xb, yb) = regexMatch!!.destructured
                datalist.add(Sensor(Point(xs.toInt(), ys.toInt()), Point(xb.toInt(), yb.toInt())))
            }
            else -> throw InputDataException("day 14 - bad line: $line")
        }
    }

    override fun process(input: List<String>): InputDay15 {
        val datalist = mutableListOf<Sensor>()
        input.forEach { line -> processLine(line, datalist) }
        return InputDay15(datalist)
    }
}