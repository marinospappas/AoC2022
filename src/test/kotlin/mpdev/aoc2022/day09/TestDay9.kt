package mpdev.aoc2022.day09

import mpdev.aoc2022.common.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.awt.Point

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 9 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay9 {

    private val filename = "src/test/resources/day09/input.txt"
    private val inputProcessor = InputProcessorDay09()
    private val solution = SolutionProcessorDay09()
    private lateinit var testPuzzle: Day09
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testPuzzle = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 9, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 9, inputLines)!!
    }

    /*
     R 4
     U 4
     L 3
     D 1
     R 4
     D 1
     L 5
     R 2
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            Pair('R', 4),
            Pair('U', 4),
            Pair('L', 3),
            Pair('D', 1),
            Pair('R', 4),
            Pair('D', 1),
            Pair('L', 5),
            Pair('R', 2),
        )
        println(testPuzzle.moves)
        assertEquals(expected.size, testPuzzle.moves.size)
        for (i in expected.indices)
            assertEquals(expected[i], testPuzzle.moves[i])
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "13"
        println("== Initial State ==")
        println(testPuzzle.grid)
        testMode = true
        val result = solution.part1(testPuzzle)
        println(testPuzzle.tailTrail.distinct())
        assertEquals(expected, result)
        testMode = false
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val filename = "src/test/resources/day09/input2.txt"
        val inputLines = getInput(filename)
        testPuzzle = inputProcessor.process(inputLines)
        puzzleProcessor2 = DaySpecific.getProcessor(2, 9, inputLines)!!
        val expected = "36"
        println("== Initial State ==")
        println(testPuzzle.grid)
        testMode = true
        doAnimation = true
        val result = solution.part2(testPuzzle)
        println(testPuzzle.tailTrail.distinct())
        assertEquals(expected, result)
        testMode = false
        assertEquals(expected, puzzleProcessor2.process())
        // animation
        testPuzzle.animateMoves()
        animationObject.title1 = "AoC 2022 Day 9 Animation"
        animationObject.title2 = "Rope Moves"
        animationObject.timerInterval = 50
        animationObject.tileSize = 30
        animationObject.gridOn = true
        animationObject.startPoint = Point(100,100)
        animationObject.rows = 23
        animationObject.columns = 28
        animationObject.waitForEnter = true
        animationObject.start()
    }
}

