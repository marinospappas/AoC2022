package mpdev.aoc2022.day20

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 20 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay20 {

    private val filename = "src/test/resources/day20/input.txt"
    private val inputProcessor = InputProcessorDay20()
    private val solution = SolutionProcessorDay20()
    private lateinit var testInput: InputDay20
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
        testInput.encrList.forEach { println(it) }
        assertEquals(7, testInput.encrList.size)
    }

    @Test
    @Order(3)
    fun `Test Shift One Item`() {
        println("Initial arrangement:")
        println(testInput.encrList)
        (0..6).forEach {
            println("\n${testInput.encrList[it].value} moves")
            testInput.shiftOneItem(it)
            println(testInput.shiftedList())
            println(testInput.encrList)
        }
    }

    @Test
    @Order(4)
    fun `Test Shift List`() {
        println("Initial arrangement:")
        println(testInput.encrList)
        testInput.shiftList()
        println("\nresult after shifting the list")
        println(testInput.shiftedList())
        println(testInput.encrList)
        assertEquals(listOf(1, 2, -3, 4, 0, 3, -2), testInput.shiftedList())
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "152"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "1514285714288"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        //assertEquals(expected, puzzleProcessor2.process())
    }

}

