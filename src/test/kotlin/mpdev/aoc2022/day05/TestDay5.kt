package mpdev.aoc2022.day05

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
    private lateinit var testInput: InputDay05
    private lateinit var puzzleProcessor1: PuzzleProcessor<InputDay05>
    private lateinit var puzzleProcessor2: PuzzleProcessor<InputDay05>

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
        [D]
    [N] [C]
    [Z] [M] [P]
     1   2   3

    move 1 from 2 to 1
    move 3 from 1 to 3
    move 2 from 2 to 1
    move 1 from 1 to 2
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expectedStacks = listOf(
            "ZN",
            "MCD",
            "P",
        )
        val expectedMoves = listOf(
            Move(1,2,1),
            Move(3,1,3),
            Move(2,2,1),
            Move(1,1,2),
        )
        println(testInput.toString())
        assertEquals(expectedStacks.size, testInput.stacks.size)
        for (i in testInput.stacks.indices)
            assertEquals(expectedStacks[i], testInput.stacks[i])
        assertEquals(expectedMoves.size, testInput.moves.size)
        for (i in testInput.moves.indices)
            assertEquals(expectedMoves[i], testInput.moves[i])
    }


    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "CMZ"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "MCD"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

