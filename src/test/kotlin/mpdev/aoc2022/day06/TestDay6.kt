package mpdev.aoc2022.day06

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.utils.allCharsDifferent
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 6 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay6 {

    private val filename = "src/test/resources/day06/input.txt"
    private val inputProcessor = InputProcessorDay06()
    private val solution = SolutionProcessorDay06()
    private lateinit var testInput: InputDay06
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 6, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 6, inputLines)!!
    }

    val exmplBuffer = listOf(
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
    )

    /*
    mjqjpqmgbljsphdztnvjfqwrcgsmlb
    */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        println(testInput.toString())
        assertEquals(expected, testInput.dataBuffer)
    }

    @Test
    @Order(5)
    fun `Test All Chars Different`() {
        assertTrue("abcd".allCharsDifferent())
        assertFalse("adcd".allCharsDifferent())
        assertFalse("abdd".allCharsDifferent())
        assertFalse("aacd".allCharsDifferent())
    }

    @Test
    @Order(8)
    fun `Test Buffer Examples Part 1`() {
        assertEquals("5", solution.part1(InputDay06(exmplBuffer[0])))
        assertEquals("6", solution.part1(InputDay06(exmplBuffer[1])))
        assertEquals("10", solution.part1(InputDay06(exmplBuffer[2])))
        assertEquals("11", solution.part1(InputDay06(exmplBuffer[3])))
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "7"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "19"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

    @Test
    @Order(14)
    fun `Test Buffer Examples Part 2`() {
        assertEquals("23", solution.part2(InputDay06(exmplBuffer[0])))
        assertEquals("23", solution.part2(InputDay06(exmplBuffer[1])))
        assertEquals("29", solution.part2(InputDay06(exmplBuffer[2])))
        assertEquals("26", solution.part2(InputDay06(exmplBuffer[3])))
    }
}

