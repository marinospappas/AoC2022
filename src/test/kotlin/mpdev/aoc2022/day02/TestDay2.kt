package mpdev.aoc2022.day02

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.system.measureTimeMillis

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 2 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay2 {

    private val filename = "src/test/resources/day02/input.txt"
    private val inputProcessor = InputProcessorDay02()
    private val solution = SolutionProcessorDay02()

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            PlayData('A', 'Y'),
            PlayData('B', 'X'),
            PlayData('C', 'Z'),
        )
        val inputLines = getInput(filename)
        val testInput: Input<PlayData> = inputProcessor.process(inputLines)
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(2)
    fun `Test RPS Result - Three dfferent methods tested`() {
        val expected = listOf(8, 1, 6)
        val inputLines = getInput(filename)
        val testInput: Input<PlayData> = inputProcessor.process(inputLines)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices) {
            assertEquals(
                expected[i], rockPaperScissorsA(testInput.inputList[i].player2, testInput.inputList[i].player1)
            )
            assertEquals(
                expected[i], rockPaperScissorsB(testInput.inputList[i].player2, testInput.inputList[i].player1)
            )
            assertEquals(
                expected[i], rockPaperScissorsC(testInput.inputList[i].player2, testInput.inputList[i].player1)
            )
        }
    }

    @Test
    @Order(3)
    fun `Test Calculate Strategy`() {
        val expected = listOf('X', 'X', 'X')
        val inputLines = getInput(filename)
        val testInput: Input<PlayData> = inputProcessor.process(inputLines)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], solution.calculateStrategy(testInput.inputList[i].player2, testInput.inputList[i].player1))
    }

    @Test
    @Order(4)
    fun `Test Calculate Part 1`() {
        val expected = 15
        val inputLines = getInput(filename)
        val testInput: Input<PlayData> = inputProcessor.process(inputLines)
        val result = solution.part1(testInput)
        assertEquals(expected, result.res)
        val puzzleProcessor = PuzzleProcessor(
            1, 2, ConstantsDay02(), inputLines, InputProcessorDay02(), SolutionProcessorDay02())
        assertEquals(expected, puzzleProcessor.process())
    }

    @Test
    @Order(5)
    fun `Test Calculate Part 2`() {
        val expected = 12
        val inputLines = getInput(filename)
        val testInput: Input<PlayData> = inputProcessor.process(inputLines)
        val result = solution.part2(testInput)
        assertEquals(expected, result.res)
        val puzzleProcessor = PuzzleProcessor(
            2, 2, ConstantsDay02(), inputLines, InputProcessorDay02(), SolutionProcessorDay02())
        assertEquals(expected, puzzleProcessor.process())
    }

    @Test
    @Order(10)
    fun `Performance Test`() {
        val inputLines = getInput(filename)
        val testInput: Input<PlayData> = inputProcessor.process(inputLines)
        var repeat = 100000000
        var elapsedTime = measureTimeMillis {
            for (i in 1..repeat) {
                for (i in testInput.inputList.indices)
                    rockPaperScissorsA(testInput.inputList[i].player2, testInput.inputList[i].player1)
            }
        }
        println("using if-then-else for $repeat repetitions: $elapsedTime msec")

        elapsedTime = measureTimeMillis {
            for (i in 1..repeat) {
                for (i in testInput.inputList.indices)
                    rockPaperScissorsB(testInput.inputList[i].player2, testInput.inputList[i].player1)
            }
        }
        println("using maths for $repeat repetitions: $elapsedTime msec")

        repeat /= 10
        elapsedTime = measureTimeMillis {
            for (i in 1..repeat) {
                for (i in testInput.inputList.indices)
                    rockPaperScissorsC(testInput.inputList[i].player2, testInput.inputList[i].player1)
            }
        }
        println("using score map for ${repeat*10} repetitions: ${elapsedTime*10} msec")
    }
}
