package mpdev.aoc2022.day15

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.utils.append
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 15 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay15 {

    private val filename = "src/test/resources/day15/input.txt"
    private val inputProcessor = InputProcessorDay15()
    private val solution = SolutionProcessorDay15()
    private lateinit var testInput: InputDay15
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 15, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 15, inputLines)!!
    }

    /*
Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        testInput.inputList.forEach { println(it) }
        assertEquals(14, testInput.inputList.size)
    }

    @Test
    @Order(3)
    fun `Test Points Of No Beacon For Sensor for Y`() {
        for (y in -4..18)
            println(testInput.grid.sensorList[6].getPointsOfNoBcnForY(y))
    }

    @Test
    @Order(4)
    fun `Test Range Of No Beacon Points For Line`() {
        val list = testInput.grid.getNoBcnRangeForY(10)
        println(list)
    }

    @Test
    @Order(5)
    fun `Test Consolidating Ranges Of No Beacon Points For Line`() {
        val list = testInput.grid.getNoBcnRangeForY(10)
        val consRange = testInput.grid.consolidateRanges(list)
        println(consRange)
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "26"
        solution.inputPart1 = 10
        val result = solution.part1(testInput)
        assertEquals(expected, result)
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "56000011"
        solution.inputPart2 = 20
        val result = solution.part2(testInput)
        assertEquals(expected, result)
    }

}

