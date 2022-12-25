package mpdev.aoc2022.day25

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.day25.InputDay25.Companion.snafuList
import mpdev.aoc2022.day25.InputDay25.Snafu
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 25 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay25 {

    private val filename = "src/test/resources/day25/input.txt"
    private val inputProcessor = InputProcessorDay25()
    private val solution = SolutionProcessorDay25()
    private lateinit var testInput: InputDay25
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 25, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 25, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        println(snafuList)
        assertEquals(13, snafuList.size)
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource(value = arrayOf(
        "1, 1",
        "2, 2",
        "3, 1=",
        "4, 1-",
        "5, 10",
        "6, 11",
        "7, 12",
        "8, 2=",
        "9, 2-",
        "10, 20",
        "15, 1=0",
        "20, 1-0",
        "2022, 1=11-2",
        "12345, 1-0---0",
        "314159265, 1121-1110-1=0"
    ))
    fun `Test Snafu To Int and Int To Snafu`(int: String, snafu: String) {
        assertEquals(int.toLong(), Snafu(snafu).toLong())
        assertEquals(snafu, int.toLong().toSnafu())
    }

    @Test
    @Order(5)
    fun `Test Snafu To Int 2`() {
        val expected = listOf(
            1747, 906, 198, 11, 201, 31, 1257, 32, 353, 107, 7, 3, 37
        )
        snafuList.indices.forEach { assertEquals(expected[it], snafuList[it].toLong()) }
        assertEquals(4890L, snafuList.sumOf { it.toLong() })
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "2=-1=0"
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

