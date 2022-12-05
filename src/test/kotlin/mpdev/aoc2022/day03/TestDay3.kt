package mpdev.aoc2022.day03

import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 3 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay3 {

    private val filename = "src/test/resources/day03/input.txt"
    private val inputProcessor = InputProcessorDay03()
    private val solution = SolutionProcessorDay03()

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
        val inputLines = getInput(filename)
        val testInput: InputDay03 = inputProcessor.process(inputLines)
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
        val inputLines = getInput(filename)
        val testInput: InputDay03 = inputProcessor.process(inputLines)
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
        val inputLines = getInput(filename)
        val testInput: InputDay03 = inputProcessor.process(inputLines)
        for (i in expected.keys)
            assertEquals(expected[i], solution.getGroups(testInput)[i])
    }

    @Test
    @Order(5)
    fun `Test Common Across Group`() {
        val expected = listOf('r', 'Z')
        val inputLines = getInput(filename)
        val testInput: InputDay03 = inputProcessor.process(inputLines)
        val groupsList = solution.getGroups(testInput)
        for (i in groupsList.keys)
            assertEquals(expected[i], solution.getCommonInGroup(groupsList[i]!!))
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "157"
        val inputLines = getInput(filename)
        val testInput: InputDay03 = inputProcessor.process(inputLines)
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        val puzzleProcessor = PuzzleProcessor(
            1, 3, ConstantsDay03(), inputLines, InputProcessorDay03(), SolutionProcessorDay03())
        assertEquals(expected, puzzleProcessor.process())
    }

    @Test
    @Order(11)
    fun `Test Calculate Part 2`() {
        val expected = "70"
        val inputLines = getInput(filename)
        val testInput: InputDay03 = inputProcessor.process(inputLines)
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        val puzzleProcessor = PuzzleProcessor(
            2, 3, ConstantsDay03(), inputLines, InputProcessorDay03(), SolutionProcessorDay03())
        assertEquals(expected, puzzleProcessor.process())
    }
}

