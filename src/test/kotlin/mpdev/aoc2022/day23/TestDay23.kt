package mpdev.aoc2022.day23

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.day23.InputDay23.Companion.grid
import mpdev.aoc2022.day23.InputDay23.Companion.movesList
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 23 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay23 {

    private val filename = "src/test/resources/day23/input.txt"
    private val inputProcessor = InputProcessorDay23()
    private val solution = SolutionProcessorDay23()
    private lateinit var testInput: InputDay23
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 23, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 23, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        println(testInput.gridToString())
        assertEquals(12, grid.size)
    }

    @Test
    @Order(3)
    fun `Test Small Example`() {
        val inputLines = listOf(
            ".....",
            "..##.",
            "..#..",
            ".....",
            "..##.",
            "....."
        )
        testInput = inputProcessor.process(inputLines)
        println(testInput.gridToString())
    }

    @Test
    @Order(3)
    fun `Test Play Round`() {
        val inputLines = listOf(
            ".....",
            "..##.",
            "..#..",
            ".....",
            "..##.",
            "....."
        )
        val expected = listOf(
            ".......",
            "...#...",
            ".....#.",
            ".#.....",
            ".....#.",
            ".......",
            "...#...",
            ".......",
            )
        testInput = inputProcessor.process(inputLines)
        println(testInput.gridToString())
        (1..3).forEach {
            testInput.playRound()
            println(testInput.gridToString())
        }
        for (i in expected.indices)
            assertEquals(expected[i].toList(), grid[i].toList())
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "110"
        val result = solution.part1(testInput)
        println(testInput.gridToString())
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = ""
        val result = solution.part2(testInput)
        println(testInput.gridToString())
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

