package mpdev.aoc2022.day02

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 3 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay2 {

    /*
    The first Elf is carrying food with 1000, 2000, and 3000 Calories, a total of 6000 Calories.
    The second Elf is carrying one food item with 4000 Calories.
    The third Elf is carrying food with 5000 and 6000 Calories, a total of 11000 Calories.
    The fourth Elf is carrying food with 7000, 8000, and 9000 Calories, a total of 24000 Calories.
    The fifth Elf is carrying one food item with 10000 Calories.
    */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            Elf(0, 6000),
            Elf(1, 4000),
            Elf(2, 11000),
            Elf(3, 24000),
            Elf(4, 10000)
        )
        val testInput: Input = getInput(arrayOf("src/test/resources/day01/input.txt"))
        assertEquals(expected.size, testInput.inputList.size)
        testInput.inputList.forEach {
            assertEquals(expected[it.id].id, it.id)
            assertEquals(expected[it.id].calories, it.calories)
        }
    }

    @Test
    @Order(2)
    fun `Test Calculate Part 1`() {
        val expected = 24000
        val testInput: Input = getInput(arrayOf("src/test/resources/day01/input.txt"))
        val result = solvePart1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(3)
    fun `Test Calculate Part 2`() {
        val expected = 45000
        val testInput: Input = getInput(arrayOf("src/test/resources/day01/input.txt"))
        val result = solvePart2(testInput)
        assertEquals(expected, result.res)
    }
}

