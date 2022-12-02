package mpdev.aoc2022.day02

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 2 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay2 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            PlayData('A', 'Y'),
            PlayData('B', 'X'),
            PlayData('C', 'Z'),
        )
        val testInput: Input = getInput(arrayOf("src/test/resources/day02/input.txt"))
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(2)
    fun `Test RPS Result`() {
        val expected = listOf(8,1,6)
        val testInput: Input = getInput(arrayOf("src/test/resources/day02/input.txt"))
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], rockPaperScisors(testInput.inputList[i].player2, testInput.inputList[i].player1))
    }

    @Test
    @Order(3)
    fun `Test Calculate Strategy`() {
        val expected = listOf('X','X','X')
        val testInput: Input = getInput(arrayOf("src/test/resources/day02/input.txt"))
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], calculateStrategy(testInput.inputList[i].player2, testInput.inputList[i].player1))
    }

    @Test
    @Order(4)
    fun `Test Calculate Part 1`() {
        val expected = 15
        val testInput: Input = getInput(arrayOf("src/test/resources/day02/input.txt"))
        val result = solvePart1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(5)
    fun `Test Calculate Part 2`() {
        val expected = 12
        val testInput: Input = getInput(arrayOf("src/test/resources/day02/input.txt"))
        val result = solvePart2(testInput)
        assertEquals(expected, result.res)
    }
}

