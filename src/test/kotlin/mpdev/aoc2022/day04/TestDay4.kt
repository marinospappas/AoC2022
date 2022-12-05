package mpdev.aoc2022.day04

import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 4 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay4 {

    private val filename = "src/test/resources/day04/input.txt"
    private val inputProcessor = InputProcessorDay04()
    private val solution = SolutionProcessorDay04()
    private lateinit var testInput: InputDay04
    private lateinit var puzzleProcessor1: PuzzleProcessor<InputDay04>
    private lateinit var puzzleProcessor2: PuzzleProcessor<InputDay04>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(getInput(filename))
        puzzleProcessor1 = PuzzleProcessor(
            1, 4, ConstantsDay04(), inputLines, InputProcessorDay04(), SolutionProcessorDay04()
        )
        puzzleProcessor2 = PuzzleProcessor(
            2, 4, ConstantsDay04(), inputLines, InputProcessorDay04(), SolutionProcessorDay04()
        )
    }

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
        println(testInput.inputList)
        assertEquals(expected.size, testInput.inputList.size)
        for (i in testInput.inputList.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(4)
    fun `Test Section contains`() {
        assertFalse(testInput.inputList[0].sectionList[0]
            .contains(testInput.inputList[0].sectionList[1]))
        assertFalse(testInput.inputList[1].sectionList[0]
            .contains(testInput.inputList[1].sectionList[1]))
        assertFalse(testInput.inputList[2].sectionList[0]
            .contains(testInput.inputList[2].sectionList[1]))
        assertTrue(testInput.inputList[3].sectionList[0]
            .contains(testInput.inputList[3].sectionList[1]))
        assertTrue(testInput.inputList[4].sectionList[1]
            .contains(testInput.inputList[4].sectionList[0]))
        assertFalse(testInput.inputList[5].sectionList[0]
            .contains(testInput.inputList[5].sectionList[1]))
    }

    @Test
    @Order(5)
    fun `Test Section overlaps`() {
        assertFalse(testInput.inputList[0].sectionList[0]
            .overlaps(testInput.inputList[0].sectionList[1]))
        assertFalse(testInput.inputList[1].sectionList[0]
            .overlaps(testInput.inputList[1].sectionList[1]))
        assertTrue(testInput.inputList[2].sectionList[0]
            .overlaps(testInput.inputList[2].sectionList[1]))
        assertTrue(testInput.inputList[3].sectionList[0]
            .overlaps(testInput.inputList[3].sectionList[1]))
        assertTrue(testInput.inputList[4].sectionList[0]
            .overlaps(testInput.inputList[4].sectionList[1]))
        assertTrue(testInput.inputList[5].sectionList[0]
            .overlaps(testInput.inputList[5].sectionList[1]))
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "2"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "4"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }
}

