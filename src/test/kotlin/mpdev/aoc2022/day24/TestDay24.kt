package mpdev.aoc2022.day24

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.day24.Day24.Companion.grid
import mpdev.aoc2022.day24.Day24.Companion.overlayGrid
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 24 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay24 {

    private val filename = "src/test/resources/day24/input.txt"
    private val inputProcessor = InputProcessorDay24()
    private val solution = SolutionProcessorDay24()
    private lateinit var testInput: Day24
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 24, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 24, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        println(grid.gridToString())
        testInput.blizzardList.forEach { println(it) }
        assertEquals(6, grid.size)
    }

    @Test
    @Order(3)
    fun `Test Blizzard Path Example`() {
        val inputLines = listOf(
            "#.#####",
            "#.....#",
            "#>....#",
            "#.....#",
            "#...v.#",
            "#.....#",
            "#####.#"
        )
        testInput = inputProcessor.process(inputLines)
        println(grid.gridToString())
        println(testInput.blizzardList)
        (0..5).forEach {
            println("$it.")
            testInput.overlay(it)
            println(overlayGrid.gridToString())
        }
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "18"
        val result = solution.part1(testInput)
        println("Start")
        testInput.overlay(0)
        println(overlayGrid.gridToString())
        println("Finish")
        testInput.overlay(18)
        println(overlayGrid.gridToString())
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "54"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

