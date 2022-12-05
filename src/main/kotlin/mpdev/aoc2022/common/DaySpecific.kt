package mpdev.aoc2022.common

import mpdev.aoc2022.day01.ConstantsDay01
import mpdev.aoc2022.day01.InputProcessorDay01
import mpdev.aoc2022.day01.SolutionProcessorDay01
import mpdev.aoc2022.day02.ConstantsDay02
import mpdev.aoc2022.day02.InputProcessorDay02
import mpdev.aoc2022.day02.SolutionProcessorDay02
import mpdev.aoc2022.day03.ConstantsDay03
import mpdev.aoc2022.day03.InputProcessorDay03
import mpdev.aoc2022.day03.SolutionProcessorDay03
import mpdev.aoc2022.day04.ConstantsDay04
import mpdev.aoc2022.day04.InputProcessorDay04
import mpdev.aoc2022.day04.SolutionProcessorDay04
import mpdev.aoc2022.day05.ConstantsDay05
import mpdev.aoc2022.day05.InputProcessorDay05
import mpdev.aoc2022.day05.SolutionProcessorDay05
import mpdev.aoc2022.day06.ConstantsDay06
import mpdev.aoc2022.day06.InputProcessorDay06
import mpdev.aoc2022.day06.SolutionProcessorDay06

class DaySpecific {
    companion object {
        fun getProcessor(part1Or2: Int, day: Int, inputlines: List<String>): PuzzleProcessor<*>? {
            return when (day) {
                1 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay01(),
                    inputlines,
                    InputProcessorDay01(),
                    SolutionProcessorDay01()
                )

                2 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay02(),
                    inputlines,
                    InputProcessorDay02(),
                    SolutionProcessorDay02()
                )

                3 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay03(),
                    inputlines,
                    InputProcessorDay03(),
                    SolutionProcessorDay03()
                )

                4 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay04(),
                    inputlines,
                    InputProcessorDay04(),
                    SolutionProcessorDay04()
                )

                5 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay05(),
                    inputlines,
                    InputProcessorDay05(),
                    SolutionProcessorDay05()
                )

                6 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay06(),
                    inputlines,
                    InputProcessorDay06(),
                    SolutionProcessorDay06()
                )

                else -> null
            }
        }
    }
}