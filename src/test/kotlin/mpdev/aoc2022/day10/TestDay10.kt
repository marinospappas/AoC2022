package mpdev.aoc2022.day10

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.common.testMode
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import kotlin.math.exp

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 9 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay10 {

    private val filename = "src/test/resources/day10/input.txt"
    private val inputProcessor = InputProcessorDay10()
    private val solution = SolutionProcessorDay10()
    private lateinit var testInput: InputDay10
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 10, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 10, inputLines)!!
    }

    /*
addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            Pair("addx", "15"),
            Pair("addx", "-11"),
            Pair("addx", "6"),
            Pair("addx", "-3"),
            Pair("addx", "5"),
            Pair("addx", "-1"),
            Pair("addx", "-8"),
            Pair("addx", "13"),
            Pair("addx", "4"),
            Pair("noop", ""),
            // ....
            Pair("noop", ""),
            )
        println(testInput.instrList)
        assertEquals(146, testInput.instrList.size)
        for (i in 0..9)
            assertEquals(expected[i], testInput.instrList[i])
        assertEquals(expected.last(), testInput.instrList.last())


    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "13140"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = ""       // not needed for day 10 - just visual check
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

