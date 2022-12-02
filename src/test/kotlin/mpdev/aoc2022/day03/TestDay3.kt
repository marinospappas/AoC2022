package mpdev.aoc2022.day03

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 3 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay3 {

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            PlayData('A', 'Y'),
        )
        val testInput: Input = getInput(arrayOf("src/test/resources/day03/input.txt"))
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = 15
        val testInput: Input = getInput(arrayOf("src/test/resources/day03/input.txt"))
        val result = solvePart1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(11)
    fun `Test Calculate Part 2`() {
        val expected = 12
        val testInput: Input = getInput(arrayOf("src/test/resources/day03/input.txt"))
        val result = solvePart2(testInput)
        assertEquals(expected, result.res)
    }
}

