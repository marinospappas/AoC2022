package mpdev.aoc2022.day04

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.PuzzleSolution
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 3 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay4 {

    private val puzzleSolution = PuzzleSolution(1, 4, "", InputLineDay04(), ConstantsDay04(), SolutionDay04())
    private val solution = SolutionDay04()

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            Rucksack("vJrwpWtwJgWr", "hcsFMMfFFhFp"),
        )
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day04/input.txt")
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(4)
    fun `Test Create Groups`() {
        val expected = listOf<Rucksack>(
        )
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        for (i in expected.indices)
            assertEquals(expected[i], solution.getGroups(testInput)[i])
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = 157
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        val result = solution.part1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(11)
    fun `Test Calculate Part 2`() {
        val expected = 70
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        val result = solution.part2(testInput)
        assertEquals(expected, result.res)
    }
}

