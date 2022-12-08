package mpdev.aoc2022.day07

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 7 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay7 {

    private val filename = "src/test/resources/day07/input.txt"
    private val inputProcessor = InputProcessorDay07()
    private val solution = SolutionProcessorDay07()
    private lateinit var testInput: InputDay07
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 7, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 7, inputLines)!!
    }

    /*
- / (dir)
  - a (dir)
    - e (dir)
      - i (file, size=584)
    - f (file, size=29116)
    - g (file, size=2557)
    - h.lst (file, size=62596)
  - b.txt (file, size=14848514)
  - c.dat (file, size=8504156)
  - d (dir)
    - j (file, size=4060174)
    - d.log (file, size=8033020)
    - d.ext (file, size=5626152)
    - k (file, size=7214296)
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        // just visual check
        println(testInput.toString())
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "95437"
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "24933642"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

