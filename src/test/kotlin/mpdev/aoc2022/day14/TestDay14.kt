package mpdev.aoc2022.day14

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.animationObject
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.awt.Point

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 14 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay14 {

    private val filename = "src/test/resources/day14/input.txt"
    private val inputProcessor = InputProcessorDay14()
    private val solution = SolutionProcessorDay14()
    private lateinit var testInput: Day14
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 14, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 14, inputLines)!!
    }

    /*
498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9


  4     5  5
  9     0  0
  4     0  3
0 ......+...
1 ..........
2 ..........
3 ..........
4 ....#...##
5 ....#...#.
6 ..###...#.
7 ........#.
8 ........#.
9 #########.

     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            listOf(Pair(498,4), Pair(498,6), Pair(496,6)),
            listOf(Pair(503,4), Pair(502,4), Pair(502,9), Pair(494,9)),

        )
        testInput.inputList.forEach { println(it) }
        println(testInput.gridToString())
        assertEquals(expected.size, testInput.inputList.size)
        for (i in expected.indices)
            assertEquals(expected[i], testInput.inputList[i])
    }

    @Test
    @Order(5)
    fun `Test Drop One Grain 1`() {
        while (testInput.dropOneGrain()) {
            println(testInput.gridToString())
            println()
            Thread.sleep(100)
        }
    }

    @Test
    @Order(5)
    fun `Test Drop One Grain 2`() {
        testInput.extendGrid2()
        while (testInput.dropOneGrain2()) {
            println(testInput.gridToString())
            println()
            Thread.sleep(20)
        }
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "24"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        //assertEquals(expected, puzzleProcessor1.process())
        // animation
        animationObject.title1 = "AoC 2022 Day 14 Animation"
        animationObject.title2 = "Pile of Sand"
        animationObject.timerInterval = 300
        animationObject.tileSize = 40
        animationObject.gridOn = true
        animationObject.startPoint = Point(200,200)
        animationObject.rows = 10
        animationObject.columns = 10
        animationObject.waitForEnter = true
        animationObject.start()
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "93"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

