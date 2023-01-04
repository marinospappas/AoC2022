package mpdev.aoc2022.day20

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 20 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay20 {

    private val filename = "src/test/resources/day20/input.txt"
    private val inputProcessor = InputProcessorDay20()
    private val solution = SolutionProcessorDay20()
    private lateinit var testInput: Day20
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 20, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 20, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        testInput.dataList.forEach { println(it) }
        assertEquals(7, testInput.dataList.size)
    }

    @Test
    @Order(4)
    fun `Test Shift List`() {
        println("Initial arrangement:")
        println(testInput.dataList)
        testInput.shiftList()
        println("\nresult after shifting the list")
        println(testInput.shiftedList())
        println(testInput.dataList)
        assertEquals(listOf(1L, 2, -3, 4, 0, 3, -2), testInput.shiftedList())
    }

    @Test
    @Order(6)
    fun `Test Shift List 2`() {
        testInput.processDecrKey()
        println("Initial arrangement:")
        println(testInput.dataList)
        (1..10).forEach { testInput.shiftList() }
        println("\nresult after shifting the list")
        println(testInput.shiftedList())
        println(testInput.dataList)
        assertEquals(listOf(0L, -2434767459, 1623178306, 3246356612, -1623178306, 2434767459, 811589153),
            testInput.shiftedList())
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "3"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "1623178306"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

