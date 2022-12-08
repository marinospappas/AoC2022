package mpdev.aoc2022.day09

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 9 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay9 {

    private val filename = "src/test/resources/day08/input.txt"
    private val inputProcessor = InputProcessorDay09()
    private val solution = SolutionProcessorDay09()
    private lateinit var testInput: InputDay09
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 9, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 9, inputLines)!!
    }

    /*

     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            ""
        )
        println(testInput.toString())
        assertEquals(expected.size, testInput.trees.size)
        for (i in expected.indices)
            assertEquals(expected[i], testInput.trees[i])

    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = ""
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

