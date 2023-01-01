package mpdev.aoc2022.day22

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.common.testMode
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 22 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay22 {

    private val filename = "src/test/resources/day22/input.txt"
    private val inputProcessor = InputProcessorDay22()
    private val solution = SolutionProcessorDay22()
    private lateinit var testInput: Day22
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        testMode = true
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 22, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 22, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        println(testInput.gridToString())
        println(testInput.path)
        assertEquals(12, testInput.grid.size)
        assertEquals(13, testInput.path.size)
    }

    @Test
    @Order(3)
    fun `Test Execute Step`() {
        println(testInput.gridToString())
        println(testInput.path)
        testInput.path.forEach {
            testInput.executeMove(it){testInput.nextPosition2D()}
            println()
            println("step $it")
            println(testInput.gridToString())
        }
    }

    @Test
    @Order(5)
    fun `Test Execute Step 3D`() {
        println(testInput.gridToString())
        println(testInput.path)
        testInput.path.forEach {
            testInput.executeMove(it){testInput.nextPosition3D()}
            println()
            println("step $it")
            println(testInput.gridToString())
            println("current position: ${testInput.position}")
        }
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "6032"
        val result = solution.part1(testInput)
        println(testInput.gridToString())
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "5031"
        val result = solution.part2(testInput)
        println(testInput.gridToString())
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

