package mpdev.aoc2022.day08

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 8 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay8 {

    private val filename = "src/test/resources/day08/input.txt"
    private val inputProcessor = InputProcessorDay08()
    private val solution = SolutionProcessorDay08()
    private lateinit var testInput: InputDay08
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 8, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 8, inputLines)!!
    }

    /*
    30373
    25512
    65332
    33549
    35390
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            listOf(3,0,3,7,3),
            listOf(2,5,5,1,2),
            listOf(6,5,3,3,2),
            listOf(3,3,5,4,9),
            listOf(3,5,3,9,0)
        )
        println(testInput.toString())
        assertEquals(expected.size, testInput.trees.size)
        for (i in expected.indices)
            assertEquals(expected[i], testInput.trees[i])

    }

    @Test
    @Order(4)
    fun `Test Visible From Edge`() {
        assertTrue(testInput.isVisible(0,1))
        assertTrue(testInput.isVisible(0,2))
        assertTrue(testInput.isVisible(testInput.numOfRows-1,1))
        assertTrue(testInput.isVisible(testInput.numOfRows-1,2))
        assertTrue(testInput.isVisible(2,0))
        assertTrue(testInput.isVisible(4,0))
        assertTrue(testInput.isVisible(3,testInput.numColumns-1))
        assertTrue(testInput.isVisible(1,testInput.numColumns-1))

    }

    @Test
    @Order(4)
    fun `Test Is Middle Tree Visible`() {
        assertTrue(testInput.isVisible(1,1))
        assertTrue(testInput.isVisible(1,2))
        assertFalse(testInput.isVisible(1,3))
        assertTrue(testInput.isVisible(2,1))
        assertFalse(testInput.isVisible(2,2))
        assertTrue(testInput.isVisible(2,3))
        assertFalse(testInput.isVisible(3,1))
        assertTrue(testInput.isVisible(3,2))
        assertFalse(testInput.isVisible(3,3))
    }

    @Test
    @Order(5)
    fun `Test Scenic Score`() {
        assertEquals(4, testInput.scenicScore(1,2))
        assertEquals(8, testInput.scenicScore(3,2))
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "21"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "8"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

