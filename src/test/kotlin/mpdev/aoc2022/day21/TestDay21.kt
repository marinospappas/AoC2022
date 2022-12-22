package mpdev.aoc2022.day21

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
@DisplayName("Day 21 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay21 {

    private val filename = "src/test/resources/day21/input.txt"
    private val inputProcessor = InputProcessorDay21()
    private val solution = SolutionProcessorDay21()
    private lateinit var testInput: InputDay21
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 21, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 21, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        println("\ninput list")
        testInput.monkeyMap.forEach { (k, v) -> println("$k: $v") }
        println("\nparent list")
        testInput.parent.forEach { (k, v) -> println("$k: $v") }
        assertEquals(15, testInput.monkeyMap.size)
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
        val expected = "301"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

