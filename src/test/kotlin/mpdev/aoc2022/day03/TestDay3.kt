package mpdev.aoc2022.day03

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.PuzzleSolution
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 3 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay3 {

    val puzzleSolution = PuzzleSolution(1, 3, "", InputLineDay03(), ConstantsDay03(), SolutionDay03())
    val solution = SolutionDay03()

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            Rucksack("vJrwpWtwJgWr", "hcsFMMfFFhFp"),
            Rucksack("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"),
            Rucksack("PmmdzqPrV", "vPwwTWBwg"),
            Rucksack("wMqvLMZHhHMvwLH", "jbvcjnnSBnvTQFn"),
            Rucksack("ttgJtRGJ", "QctTZtZT"),
            Rucksack("CrZsJsPPZsGz", "wwsLwLmpwMDw"),
        )
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(2)
    fun `Test Common Items`() {
        val expected = listOf(
            listOf('p'),
            listOf('L'),
            listOf('P'),
            listOf('v'),
            listOf('t'),
            listOf('s'),
        )
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], solution.getCommonItems(testInput.inputList[i].comp1, testInput.inputList[i].comp2))
    }

    @Test
    @Order(3)
    fun `Test Priorities`() {
        for (c in 'a'..'z')
            assertEquals(c.code-'a'.code+1, solution.getPriority(c))
        for (c in 'A'..'Z')
            assertEquals(c.code-'A'.code+27, solution.getPriority(c))
    }

    @Test
    @Order(4)
    fun `Test Create Groups`() {
        val expected = mapOf(
            0 to listOf(Rucksack("vJrwpWtwJgWr", "hcsFMMfFFhFp"),
                    Rucksack("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"),
                    Rucksack("PmmdzqPrV", "vPwwTWBwg")),
            1 to listOf(Rucksack("wMqvLMZHhHMvwLH", "jbvcjnnSBnvTQFn"),
                    Rucksack("ttgJtRGJ", "QctTZtZT"),
                    Rucksack("CrZsJsPPZsGz", "wwsLwLmpwMDw")),
        )
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        for (i in expected.keys)
            assertEquals(expected[i], solution.getGroups(testInput)[i])
    }

    @Test
    @Order(5)
    fun `Test Common Across Group`() {
        val expected = listOf('r', 'Z')
        val testInput: Input<Rucksack> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        val groupsList = solution.getGroups(testInput)
        for (i in groupsList.keys)
            assertEquals(expected[i], solution.getCommonInGroup(groupsList[i]!!))
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

