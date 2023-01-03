package mpdev.aoc2022.day16

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 15 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay16 {

    private val filename = "src/test/resources/day16/input.txt"
    private val inputProcessor = InputProcessorDay16()
    private val solution = SolutionProcessorDay16()
    private lateinit var testInput: Day16
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 16, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 16, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        testInput.valveMap.forEach { println(it) }
        println(testInput.graph)
        assertEquals(10, testInput.valveMap.size)
    }

    @Test
    @Order(3)
    fun `Test Calc Min Distances`() {
        testInput.calculateMinPathCombinations()
        testInput.minPaths.forEach{(k,v) -> println("valve pair: $k, time to reach dest: $v")}
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "1651"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "1707"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

