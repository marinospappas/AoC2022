package mpdev.aoc2022.day17

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 17 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay17 {

    private val filename = "src/test/resources/day17/input.txt"
    private val inputProcessor = InputProcessorDay17()
    private val solution = SolutionProcessorDay17()
    private lateinit var testInput: InputDay17
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 17, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 17, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        assertEquals(expected, testInput.movesList.joinToString(""))
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource(value = arrayOf("0", "1", "2", "3", "4"))
    fun `Test New Rock`(indx: Int) {
        testInput.newRock(testInput.rockList[indx])
        println(testInput.gridToString())
    }

    @ParameterizedTest
    @Order(4)
    @CsvSource(value = arrayOf("0", "1", "2", "3", "4"))
    fun `Test Move Rock Down`(indx: Int) {
        val rock = testInput.rockList[indx]
        testInput.newRock(rock)
        println(testInput.gridToString())
        Thread.sleep(1000)
        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(1000)
        }
    }

    @ParameterizedTest
    @Order(5)
    @CsvSource(value = arrayOf("0", "1", "2", "3", "4"))
    fun `Test Move Rock Sideways`(indx: Int) {
        val rock = testInput.rockList[indx]
        testInput.newRock(rock)
        println(testInput.gridToString())
        Thread.sleep(1000)
        while (testInput.moveRockSideWays('>')) {
            println(testInput.gridToString())
            Thread.sleep(1000)
        }
        while (testInput.moveRockSideWays('>')) {
            println(testInput.gridToString())
            Thread.sleep(1000)
        }
    }

    @Test
    @Order(6)
    fun `Test Move 2 Rocks Down`() {
        val rock1 = testInput.rockList[2]
        val rock2 = testInput.rockList[3]
        testInput.newRock(rock1)
        println(testInput.gridToString())
        Thread.sleep(1000)
        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(1000)
        }

        testInput.placeRockToRest()
        testInput.newRock(rock2)
        println(testInput.gridToString())
        println(testInput.currHeight)
        Thread.sleep(1000)

        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(1000)
        }
        testInput.placeRockToRest()
        println(testInput.gridToString())
        println(testInput.currHeight)
    }

    @Test
    @Order(6)
    fun `Test Move Rocks Down and Sideways`() {
        val delay = 400L
        val rock1 = testInput.rockList[2]
        val rock2 = testInput.rockList[3]
        val rock3 = testInput.rockList[4]
        val rock4 = testInput.rockList[1]
        testInput.newRock(rock1)
        println(testInput.gridToString())
        Thread.sleep(delay)
        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }
        testInput.moveRockSideWays('<')
        println(testInput.gridToString())
        Thread.sleep(delay)

        testInput.placeRockToRest()
        println(testInput.currHeight)
        Thread.sleep(delay)

        testInput.newRock(rock2)
        println(testInput.gridToString())
        println(testInput.currHeight)
        Thread.sleep(delay)

        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }

        while (testInput.moveRockSideWays('<')) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }

        while (testInput.moveRockSideWays('>')) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }

        testInput.placeRockToRest()
        println(testInput.gridToString())
        println(testInput.currHeight)

        testInput.newRock(rock3)
        println(testInput.gridToString())
        println(testInput.currHeight)
        Thread.sleep(delay)
        while (testInput.moveRockSideWays('>')) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }

        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }
        testInput.placeRockToRest()
        println(testInput.gridToString())
        println(testInput.currHeight)

        testInput.newRock(rock4)
        println(testInput.gridToString())
        println(testInput.currHeight)
        Thread.sleep(delay)
        while (testInput.moveRockSideWays('>')) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }
        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }
        while (testInput.moveRockSideWays('<')) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }
        while (testInput.moveRockDown()) {
            println(testInput.gridToString())
            Thread.sleep(delay)
        }
        testInput.placeRockToRest()
        println(testInput.gridToString())
        println(testInput.currHeight)
    }

    @ParameterizedTest
    @Order(8)
    @CsvSource(value = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
    fun `Test Play Tetris`(n: Int) {
        val result = testInput.playTetris(n)
        println(testInput.gridToString(testInput.currHeight))
        println(result)
        println()
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "3068"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "1514285714288"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        //assertEquals(expected, puzzleProcessor2.process())
    }

}

