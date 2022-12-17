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
import mpdev.aoc2022.day07.ConstantsDay07
import mpdev.aoc2022.day07.InputProcessorDay07
import mpdev.aoc2022.day07.SolutionProcessorDay07
import mpdev.aoc2022.day08.ConstantsDay08
import mpdev.aoc2022.day08.InputProcessorDay08
import mpdev.aoc2022.day08.SolutionProcessorDay08
import mpdev.aoc2022.day09.ConstantsDay09
import mpdev.aoc2022.day09.InputProcessorDay09
import mpdev.aoc2022.day09.SolutionProcessorDay09
import mpdev.aoc2022.day10.ConstantsDay10
import mpdev.aoc2022.day10.InputProcessorDay10
import mpdev.aoc2022.day10.SolutionProcessorDay10
import mpdev.aoc2022.day11.ConstantsDay11
import mpdev.aoc2022.day11.InputProcessorDay11
import mpdev.aoc2022.day11.SolutionProcessorDay11
import mpdev.aoc2022.day12.ConstantsDay12
import mpdev.aoc2022.day12.InputProcessorDay12
import mpdev.aoc2022.day12.SolutionProcessorDay12
import mpdev.aoc2022.day13.ConstantsDay13
import mpdev.aoc2022.day13.InputProcessorDay13
import mpdev.aoc2022.day13.SolutionProcessorDay13
import mpdev.aoc2022.day14.ConstantsDay14
import mpdev.aoc2022.day14.InputProcessorDay14
import mpdev.aoc2022.day14.SolutionProcessorDay14
import mpdev.aoc2022.day15.ConstantsDay15
import mpdev.aoc2022.day15.InputProcessorDay15
import mpdev.aoc2022.day15.SolutionProcessorDay15
import mpdev.aoc2022.day16.ConstantsDay16
import mpdev.aoc2022.day16.InputProcessorDay16
import mpdev.aoc2022.day16.SolutionProcessorDay16
import mpdev.aoc2022.day17.ConstantsDay17
import mpdev.aoc2022.day17.InputProcessorDay17
import mpdev.aoc2022.day17.SolutionProcessorDay17

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

                7 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay07(),
                    inputlines,
                    InputProcessorDay07(),
                    SolutionProcessorDay07()
                )

                8 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay08(),
                    inputlines,
                    InputProcessorDay08(),
                    SolutionProcessorDay08()
                )

                9 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay09(),
                    inputlines,
                    InputProcessorDay09(),
                    SolutionProcessorDay09()
                )

                10 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay10(),
                    inputlines,
                    InputProcessorDay10(),
                    SolutionProcessorDay10()
                )

                11 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay11(),
                    inputlines,
                    InputProcessorDay11(),
                    SolutionProcessorDay11()
                )

                12 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay12(),
                    inputlines,
                    InputProcessorDay12(),
                    SolutionProcessorDay12()
                )

                13 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay13(),
                    inputlines,
                    InputProcessorDay13(),
                    SolutionProcessorDay13()
                )

                14 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay14(),
                    inputlines,
                    InputProcessorDay14(),
                    SolutionProcessorDay14()
                )

                15 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay15(),
                    inputlines,
                    InputProcessorDay15(),
                    SolutionProcessorDay15()
                )

                16 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay16(),
                    inputlines,
                    InputProcessorDay16(),
                    SolutionProcessorDay16()
                )

                17 -> PuzzleProcessor(
                    part1Or2,
                    day,
                    ConstantsDay17(),
                    inputlines,
                    InputProcessorDay17(),
                    SolutionProcessorDay17()
                )

                else -> null
            }
        }
    }
}