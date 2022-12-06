package mpdev.aoc2022.day07

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 7 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay7 {

    private val filename = "src/test/resources/day07/input.txt"
    private val inputProcessor = InputProcessorDay07()
    private val solution = SolutionProcessorDay07()
    private lateinit var testInput: InputDay05
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 7, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 7, inputLines)!!
    }

    /*

     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            "",
        )
        println(testInput.toString())
        assertEquals(expected.size, testInput.stacks.size)
        for (i in testInput.stacks.indices)
            assertEquals(expected[i], testInput.stacks[i])
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "CMZ"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "MCD"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

