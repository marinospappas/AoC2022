package mpdev.aoc2022.day22

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

/*
        ...#
        .#..
..#....#....
..........#.
        .#......
        ......#.

10R5L5R10L4R5L5
 */
class InputProcessorDay22: InputProcessor<InputDay22>() {

    private fun processPath(line: String, path: MutableList<String>) {
        var s = line
        while (s.isNotEmpty()) {
            if (s.matchRegExp(Regex("""^(\d+)(.*)$"""))) {
                val (n, rest) = regexMatch!!.destructured
                path.add(n)
                s = rest
            }
            else
                if (s.matchRegExp(Regex("""^([LR])(.*)$"""))) {
                    val (dir, rest) = regexMatch!!.destructured
                    path.add(dir)
                    s = rest
                }
        }
    }

    override fun process(input: List<String>): InputDay22 {
        val datalist = mutableListOf<String>()
        val path = mutableListOf<String>()
        run breakLoop@{
            input.forEach { if (it.isEmpty()) return@breakLoop else datalist.add(it) }
        }
        processPath(input.last(), path)
        return InputDay22(datalist, path)
    }
}