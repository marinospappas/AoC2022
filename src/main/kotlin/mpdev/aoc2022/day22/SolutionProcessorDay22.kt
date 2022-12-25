package mpdev.aoc2022.day22

import mpdev.aoc2022.common.*

class SolutionProcessorDay22: SolutionProcessor<InputDay22> {

    /** part 1 calculation */
    override fun part1(input: InputDay22): String {
        input.path.forEach { input.executeMove(it){input.nextPosition2D()} }
        println("final position ${input.position}")
        return input.position.getPassword().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay22): String {
        input.path.forEach { input.executeMove(it){input.nextPosition3D()} }
        println("final position for 3D path ${input.position}")
        return input.position.getPassword().toString()    }

}