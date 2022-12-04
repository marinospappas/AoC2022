package mpdev.aoc2022.day05

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 5 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay5 {

    private val filename = "src/test/resources/day05/input.txt"
    private val inputProcessor = InputProcessorDay05()
    private val solution = SolutionProcessorDay05()
    private lateinit var testInput: Input<GroupedSections>
    private lateinit var puzzleProcessor1: PuzzleProcessor<GroupedSections>
    private lateinit var puzzleProcessor2: PuzzleProcessor<GroupedSections>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(getInput(filename))
        puzzleProcessor1 = PuzzleProcessor(
            1, 5, ConstantsDay05(), inputLines, InputProcessorDay05(), SolutionProcessorDay05())
        puzzleProcessor2 = PuzzleProcessor(
            2, 5, ConstantsDay05(), inputLines, InputProcessorDay05(), SolutionProcessorDay05())
    }

    /*

     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf<String>(

        )
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }


    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = 2
        val result = solution.part1(testInput)
        assertEquals(expected, result.res)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = 4
        val result = solution.part2(testInput)
        assertEquals(expected, result.res)
        assertEquals(expected, puzzleProcessor2.process())
    }
}

