package mpdev.aoc2022.day18

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.common.testMode
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 18 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay18 {

    private val filename = "src/test/resources/day18/input.txt"
    private val inputProcessor = InputProcessorDay18()
    private val solution = SolutionProcessorDay18()
    private lateinit var testInput: Day18
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        testMode = true
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 18, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 18, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        testInput.pointsList.forEach { println(it) }
        assertEquals(13, testInput.pointsList.size)
    }

    @Test
    @Order(3)
    fun `Test Surface Example`() {
        val example = listOf("1,1,1", "2,1,1")
        testInput = inputProcessor.process(example)
        testInput.pointsList.forEach { println(it) }
        assertEquals(10, testInput.getExposedSurface())
    }

    @Test
    @Order(4)
    fun `Test Surface Example 2`() {
        val example = listOf(
            "0,0,0", "0,1,0", "0,2,0",
            "1,0,0", "1,1,0", "1,2,0",
            "2,0,0", "2,1,0", "2,2,0",
            "0,1,1", "1,1,1", "2,1,1",
            "1,2,1"
            )
        testInput = inputProcessor.process(example)
        testInput.pointsList.forEach { println(it) }
        assertEquals(40, testInput.getExposedSurface())
    }

    @Test
    @Order(5)
    fun `Test Surface Example 3`() {
        val example = listOf(
            "1,1,0",
            "1,0,1", "0,1,1", "2,1,1", "1,2,1",
            "1,1,2"
        )
        testInput = inputProcessor.process(example)
        testInput.pointsList.forEach { println(it) }
        assertEquals(36, testInput.getExposedSurface())
        assertEquals(30, testInput.getExposedSurface(true))
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "64"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "58"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

