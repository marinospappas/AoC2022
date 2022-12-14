package mpdev.aoc2022.day13

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

/*
[1,1,3,1,1]
[1,1,5,1,1]
...
[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]
 */
class InputProcessorDay13: InputProcessor<InputDay13>() {

    override fun process(input: List<String>): InputDay13 {
        val datalist = mutableListOf<Pair<String,String>>()
        (input+"").chunked(3).forEach {
            if (it[2] == "") datalist.add(Pair(it[0], it[1]))
            else throw InputDataException("bad line - should have been empty - ${it[2]}") }
        return InputDay13(datalist)
    }
}