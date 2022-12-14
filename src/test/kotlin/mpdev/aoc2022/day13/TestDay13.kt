package mpdev.aoc2022.day13

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.convertToString
import mpdev.aoc2022.utils.ropeannimation.RopeFrame
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 12 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay13 {

    private val filename = "src/test/resources/day13/input.txt"
    private val inputProcessor = InputProcessorDay13()
    private val solution = SolutionProcessorDay13()
    private lateinit var testInput: InputDay13
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 13, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 13, inputLines)!!
    }

    /*
[1,1,3,1,1]
[1,1,5,1,1]
[[1],[2,3,4]]
[[1],4]
[9]
[[8,7,6]]
[[4,4],4,4]
[[4,4],4,4,4]
[7,7,7,7]
[7,7,7]
[]
[3]
[[[]]]
[[]]
[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]
     */

    @Test
    @Order(1)
    fun `Test Get Input`() {
        val expected = listOf(
            Pair("[1,1,3,1,1]", "[1,1,5,1,1]"),
            Pair("[[1],[2,3,4]]", "[[1],4]"),
            Pair("[9]", "[[8,7,6]]"),
            Pair("[[4,4],4,4]", "[[4,4],4,4,4]"),
            Pair("[7,7,7,7]", "[7,7,7]"),
            Pair("[]", "[3]"),
            Pair("[[[]]]", "[[]]"),
            Pair("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]")
        )
        println(testInput.packetList.convertToString())
        assertEquals(expected.size, testInput.packetList.size)
        for (i in expected.indices) {
            assertEquals(TokenListUtils.tokenise(expected[i].first), testInput.packetList[i].first.data)
            assertEquals(TokenListUtils.tokenise(expected[i].second), testInput.packetList[i].second.data)
        }
    }

    @ParameterizedTest
    @Order(2)
    @CsvSource(value = ["'[1,1,3,1,1]', 5", "'[1,1,5,1,1]', 5", "'[[1],[2,3,4]]', 2", "'[[1],4]', 2", "'[9]', 1",
        "'[[8,7,6]]', 1", "'[[4,4],4,4]', 3", "'[[4,4],4,4,4]', 4", "'[7,7,7,7]', 4", "'[7,7,7]', 3", "'[]', 0", "'[3]', 1",
        "'[[[]]]', 1", "'[[]]', 1", "'[1,[2,[3,[4,[5,6,7]]]],8,9]', 4", "'[1,[2,[3,[4,[5,6,0]]]],8,9]', 4"])
    fun `Test Get Number of Tokens in List`(list: String, res: Int) {
        assertEquals(res, TokenListUtils.numberOfTokens(TokenListUtils.tokenise(list)))
    }

    @ParameterizedTest
    @Order(2)
    @CsvSource(value = [
        "'[1,1,3,1,1]', '1', '[1,3,1,1]'",
        "'[[1],[2,3,4]]', '[1]', '[[2,3,4]]'",
        "'[[1],4]', '[1]', '[4]'",
        "'[9]', '9', ''",
        "'[[8,7,6]]', '[8,7,6]', ''",
        "'[[4,4],4,4]', '[4,4]', '[4,4]'",
        "'[]', '', ''",
        "'[[[]]]', '[[]]', ''",
        "'[[]]', '[]', ''",
        "'[1,[2,[3,[4,[5,6,0]]]],8,9]', '1', '[[2,[3,[4,[5,6,0]]]],8,9]'"])
    fun `Test Split Token List`(list: String, resA: String, resB: String) {
        println("first exp")
        TokenListUtils.tokenise(resA).forEach { println(it) }
        println("first actual")
        TokenListUtils.getFirstObject(TokenListUtils.tokenise(list)).forEach { println(it) }
        println("second exp")
        TokenListUtils.tokenise(resB).forEach { println(it) }
        println("second actual")
        TokenListUtils.getNextPart(TokenListUtils.tokenise(list)).forEach { println(it) }
        assertEquals(TokenListUtils.tokenise(resA), TokenListUtils.getFirstObject(TokenListUtils.tokenise(list)))
        assertEquals(TokenListUtils.tokenise(resB), TokenListUtils.getNextPart(TokenListUtils.tokenise(list)))
    }

    @ParameterizedTest
    @Order(6)
    @CsvSource(value = ["'[1,1,3,1,1]', '[1,1,5,1,1]', -2", "'[[1],[2,3,4]]', '[[1],4]', -2", "'[9]', '[[8,7,6]]', 1",
        "'[[4,4],4,4]', '[[4,4],4,4,4]', -1", "'[7,7,7,7]', '[7,7,7]', 1", "'[]', '[3]', -1", "'[[[]]]', '[[]]', 1",
        "'[1,[2,[3,[4,[5,6,7]]]],8,9]', '[1,[2,[3,[4,[5,6,0]]]],8,9]', 7"])
    fun `Test Get Compare Token List`(a: String, b: String, res: Int) {
        assertEquals(res, TokenListUtils.compare(TokenListUtils.tokenise(a), TokenListUtils.tokenise(b)))
    }


    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = "13"
        println(testInput.packetList)
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = "140"
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

}

