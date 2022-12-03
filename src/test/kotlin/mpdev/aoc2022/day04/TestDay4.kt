package mpdev.aoc2022.day04

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 4 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay4 {

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
        val testInput: Input = getInput(arrayOf("src/test/resources/day03/input.txt"))
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

   @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = 157
        val testInput: Input = getInput(arrayOf("src/test/resources/day03/input.txt"))
        val result = solvePart1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(11)
    fun `Test Calculate Part 2`() {
        val expected = 70
        val testInput: Input = getInput(arrayOf("src/test/resources/day03/input.txt"))
        val result = solvePart2(testInput)
        assertEquals(expected, result.res)
    }
}

