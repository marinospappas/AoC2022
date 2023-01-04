package mpdev.aoc2022.day19

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 19 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay19 {

    private val filename = "src/test/resources/day19/input.txt"
    private val inputProcessor = InputProcessorDay19()
    private val solution = SolutionProcessorDay19()
    private lateinit var testData: Day19
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testData = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 19, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 19, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        testData.blueprintList.forEach { println(it) }
        assertEquals(2, testData.blueprintList.size)
    }

    @Test
    @Order(5)
    fun `Test Calculate Geodes`() {
        val res1 = testData.calculateGeodes(testData.blueprintList[0], 24)
        val res2 = testData.calculateGeodes(testData.blueprintList[1], 24)
        println("blueprint 1: $res1")
        println("blueprint 2: $res2")
        assertEquals(9, res1)
        assertEquals(12, res2)
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "33"
        val result = solution.part1(testData)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = (56*62).toString()
        val result = solution.part2(testData)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

