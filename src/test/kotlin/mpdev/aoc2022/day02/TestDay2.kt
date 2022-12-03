package mpdev.aoc2022.day02

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.PuzzleSolution
import mpdev.aoc2022.day01.ConstantsDay01
import mpdev.aoc2022.day01.Elf
import mpdev.aoc2022.day01.InputLineDay01
import mpdev.aoc2022.day01.SolutionDay01
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.system.measureTimeMillis

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 2 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay2 {

    private val puzzleSolution = PuzzleSolution(1, 2, "", InputLineDay02(), ConstantsDay02(), SolutionDay02())
    private val solution = SolutionDay02()

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            PlayData('A', 'Y'),
            PlayData('B', 'X'),
            PlayData('C', 'Z'),
        )
        val testInput: Input<PlayData> = puzzleSolution.getInput("src/test/resources/day02/input.txt")
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(2)
    fun `Test RPS Result - Three dfferent methods tested`() {
        val expected = listOf(8, 1, 6)
        val testInput: Input<PlayData> = puzzleSolution.getInput("src/test/resources/day02/input.txt")
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
        val testInput: Input<PlayData> = puzzleSolution.getInput("src/test/resources/day02/input.txt")
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], solution.calculateStrategy(testInput.inputList[i].player2, testInput.inputList[i].player1))
    }

    @Test
    @Order(4)
    fun `Test Calculate Part 1`() {
        val expected = 15
        val testInput: Input<PlayData> = puzzleSolution.getInput("src/test/resources/day02/input.txt")
        val result = solution.part1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(5)
    fun `Test Calculate Part 2`() {
        val expected = 12
        val testInput: Input<PlayData> = puzzleSolution.getInput("src/test/resources/day02/input.txt")
        val result = solution.part2(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(10)
    fun `Performance Test`() {
        val testInput: Input<PlayData> = puzzleSolution.getInput("src/test/resources/day02/input.txt")
        val repeat = 100000000
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

        elapsedTime = measureTimeMillis {
            for (i in 1..repeat) {
                for (i in testInput.inputList.indices)
                    rockPaperScissorsC(testInput.inputList[i].player2, testInput.inputList[i].player1)
            }
        }
        println("using score map for $repeat repetitions: $elapsedTime msec")
    }
}
