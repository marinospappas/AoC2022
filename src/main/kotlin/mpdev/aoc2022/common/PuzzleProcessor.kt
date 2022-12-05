package mpdev.aoc2022.common

class PuzzleProcessor<I> (var part1Or2: Int,
                          var day: Int,
                          var constants: Constants,
                          var input: List<String>,
                          var inputProcessor: InputProcessor<I>,
                          var solution: SolutionProcessor<I>) {

    /** get puzzle input */

    fun process(): String? {
        println("${Constants.AOC} - Day $day, ${constants.PUZZLE}, Part $part1Or2 - ${Constants.AUTHOR} - ${constants.DATE}")
        val inputData = inputProcessor.process(input)
        if (part1Or2 == 1) {
            val result1 = solution.part1(inputData)
            println("${constants.RESULT_STRING}: ${result1}")
            return result1
        }
        if (part1Or2 == 2) {
            val result2 = solution.part2(inputData)
            println("${constants.RESULT_STRING2}: ${result2}")
            return result2
        }
        return null
    }

}

