package mpdev.aoc2022.day11

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.convertToString
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 11 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay11 {

    private val filename = "src/test/resources/day11/input.txt"
    private val inputProcessor = InputProcessorDay11()
    private val solution = SolutionProcessorDay11()
    private lateinit var testInput: InputDay11
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 11, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 11, inputLines)!!
    }

    /*

     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = 4
        println(testInput.monkeyList.convertToString())
        assertEquals(expected, testInput.monkeyList.size)
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "10605"
        testMode = true
        println("initial state")
        println(testInput.monkeyList.convertToString())
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "2713310158"
        testMode = true
        println("initial state\n${testInput.monkeyList}")
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

