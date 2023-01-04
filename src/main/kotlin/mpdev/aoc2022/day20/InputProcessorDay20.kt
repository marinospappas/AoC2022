package mpdev.aoc2022.day20

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay20: InputProcessor<Day20>() {

    override fun process(input: List<String>): Day20 {
        val dataList = mutableListOf<Item>()
        var count = 0
        input.forEach {
            val n: Long
            try { n = it.toLong() }
            catch (e: NumberFormatException) { throw InputDataException("day 20: bad input line $it") }
            dataList.add(Item(n, count++))
        }
        return Day20(dataList)
    }
}