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

    private val filename = "src/test/resources/day04/input.txt"
    private val puzzleSolution = PuzzleSolution(1, 4, "", InputLineDay04(), ConstantsDay04(), SolutionDay04())
    private val solution = SolutionDay04()

    /*
    2-4,6-8
    2-3,4-5
    5-7,7-9
    2-8,3-7
    6-6,4-6
    2-6,4-8
     */
    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            GroupedSections(listOf(Section(2,4), Section(6,8))),
            GroupedSections(listOf(Section(2,3), Section(4,5))),
            GroupedSections(listOf(Section(5,7), Section(7,9))),
            GroupedSections(listOf(Section(2,8), Section(3,7))),
            GroupedSections(listOf(Section(6,6), Section(4,6))),
            GroupedSections(listOf(Section(2,6), Section(4,8))),
        )
        val testInput: Input<GroupedSections> = puzzleSolution.getInput(filename)
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    //@Test
    @Order(4)
    fun `Test Create Groups`() {
        val expected = listOf<GroupedSections>(
        )
        val testInput: Input<GroupedSections> = puzzleSolution.getInput("src/test/resources/day03/input.txt")
        for (i in expected.indices)
            ;
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = 2
        val testInput: Input<GroupedSections> = puzzleSolution.getInput(filename)
        println(testInput.inputList)
        val result = solution.part1(testInput)
        assertEquals(expected, result.res)
    }

    @Test
    @Order(11)
    fun `Test Calculate Part 2`() {
        val expected = 4
        val testInput: Input<GroupedSections> = puzzleSolution.getInput(filename)
        val result = solution.part2(testInput)
        assertEquals(expected, result.res)
    }
}

