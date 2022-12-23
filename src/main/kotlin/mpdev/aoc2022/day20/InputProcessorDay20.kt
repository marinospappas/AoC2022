package mpdev.aoc2022.day20

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay20: InputProcessor<InputDay20>() {

    override fun process(input: List<String>): InputDay20 {
        val dataList = mutableListOf<Item>()
        var count = 0
        input.forEach {
            val n: Int
            try { n = it.toInt() }
            catch (e: NumberFormatException) { throw InputDataException("day 20: bad input line $it") }
            dataList.add(Item(n, count++))
        }
        return InputDay20(dataList)
    }
}