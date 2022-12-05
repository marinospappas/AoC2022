package mpdev.aoc2022.common

class PuzzleProcessor<I> (var part1Or2: Int,
                          var day: Int,
                          var constants: Constants,
                          var input: List<String>,
                          var inputProcessor: InputProcessor<I>,
                          var solution: SolutionProcessor<I>) {

    /** get puzzle input */

    fun process(): String? {
        println("${Constants.AOC} - Day $day, ${constants.PUZZLE} - ${Constants.AUTHOR} - ${constants.DATE}")
        val inputData = inputProcessor.process(input)
        if (part1Or2 == 1) {
            println("executing Part $part1Or2: ${constants.RESULT_STRING}")
            val result1 = solution.part1(inputData)
            println("result: ${result1}")
            return result1
        }
        if (part1Or2 == 2) {
            println("executing Part $part1Or2: ${constants.RESULT_STRING}")
            val result2 = solution.part2(inputData)
            println("result: ${result2}")
            return result2
        }
        return null
    }

}

