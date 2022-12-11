package mpdev.aoc2022.day12

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.ropeannimation.RopeFrame
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 12 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay12 {

    private val filename = "src/test/resources/day12/input.txt"
    private val inputProcessor = InputProcessorDay12()
    private val solution = SolutionProcessorDay12()
    private lateinit var testInput: InputDay12
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 12, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 12, inputLines)!!
    }

    /*

     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            ""
        )
        println(testInput.monkeyList.convertToString())
        assertEquals(expected.size, testInput.monkeyList.size)
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = ""
        println(testInput.monkeyList.convertToString())
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = ""
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

