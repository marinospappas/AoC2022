package mpdev.aoc2022.day10

val NOP = "noop"
val ADD = "addx"

class InputDay10(var instrList: List<Pair<String,Int>>, numOfLines: Int) {
    val crt = Crt(numOfLines)
}

class Crt(var numOfLines: Int) {

    private val PIXELS_PER_LINE = 40
    val scanner = mutableListOf(0)     // clock 0 - start
    val screen = mutableListOf<MutableList<Char>>()

    fun scanScreen(instrList: List<Pair<String, Int>>) {
        scanner.add(1) // fist cycle
        instrList.forEach { instr ->
            when (instr.first) {
                NOP -> scanner.add(scanner[scanner.lastIndex])
                ADD -> {
                    scanner.add(scanner[scanner.lastIndex])
                    scanner.add(scanner[scanner.lastIndex] + instr.second)
                }
            }
        }
    }

    fun draw() {
        (0 until numOfLines).forEach { line ->
            val screenLine = mutableListOf<Char>()
            (0 until PIXELS_PER_LINE).forEach { pixel ->
                val x = scanner[40 * line + pixel + 1]
                screenLine.add( if (pixel in (x-1)..(x+1)) '#' else '.' )
            }
            screen.add(screenLine)
        }
    }

}





