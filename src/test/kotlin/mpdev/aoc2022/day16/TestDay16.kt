package mpdev.aoc2022.day16

import mpdev.aoc2022.common.DaySpecific
import mpdev.aoc2022.common.PuzzleProcessor
import mpdev.aoc2022.common.getInput
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 15 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestDay16 {

    private val filename = "src/test/resources/day16/input.txt"
    private val inputProcessor = InputProcessorDay16()
    private val solution = SolutionProcessorDay16()
    private lateinit var testInput: InputDay16
    private lateinit var puzzleProcessor1: PuzzleProcessor<*>
    private lateinit var puzzleProcessor2: PuzzleProcessor<*>

    @BeforeEach
    fun testSetup() {
        val inputLines = getInput(filename)
        testInput = inputProcessor.process(inputLines)
        puzzleProcessor1 = DaySpecific.getProcessor(1, 16, inputLines)!!
        puzzleProcessor2 = DaySpecific.getProcessor(2, 16, inputLines)!!
    }

    @Test
    @Order(1)
    fun `Test Get Input`() {
        testInput.valveMap.forEach { println(it) }
        println(testInput.graph)
        assertEquals(10, testInput.valveMap.size)
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("providerOfArgsToCalcFlow")
    fun `Test Calculate Flow`(path: List<Pair<String,Int>>, expected: Pair<Int,Int>) {
        val flowAndTime = testInput.calculateFlowAndTime(path)
        assertEquals(expected, flowAndTime)
    }

    @Test
    @Order(10)
    fun `Test Calculate Part 1`() {
        val expected = ""
        val result = solution.part1(testInput)
        assertEquals(expected, result)
        //assertEquals(expected, puzzleProcessor1.process())
    }

    @Test
    @Order(12)
    fun `Test Calculate Part 2`() {
        val expected = ""
        val result = solution.part2(testInput)
        assertEquals(expected, result)
        assertEquals(expected, puzzleProcessor2.process())
    }

    companion object {
        @JvmStatic
        fun providerOfArgsToCalcFlow(): Stream<Arguments> {
            val totalPath = listOf(
                Pair("AA",0), Pair("DD",0), Pair("DD",1), Pair("CC",0), Pair("BB",0), Pair("BB",1),
                Pair("AA",0), Pair("JJ",0), Pair("JJ",0), Pair("JJ",1), Pair("II",0), Pair("AA",0),
                Pair("DD", 1), Pair("EE",0), Pair("FF",0), Pair("GG",0), Pair("HH",0), Pair("HH",1),
                Pair("GG",0), Pair("FF", 0), Pair("EE",0), Pair("EE",1), Pair("DD",1), Pair("CC",0),
                Pair("CC",1), Pair("DD",1), Pair("DD",1), Pair("DD",1), Pair("DD",1), Pair("DD",1),
                Pair("DD",1)
            )
            val expected = listOf(
                0, 0, 0,   20, 20, 20,   33, 33, 33, 33,   54, 54, 54, 54, 54, 54, 54, 54,   76, 76, 76, 76,
                79, 79, 79,   81, 81, 81, 81, 81, 81
            )
            val curPath = mutableListOf<Pair<String,Int>>()
            var curExpected = 0
            val args = mutableListOf<Arguments>()
            for (indx in totalPath.indices) {
                val thisPath = curPath + totalPath[indx]
                curPath.add(totalPath[indx])
                curExpected += expected[indx]
                args.add(Arguments.of(thisPath, Pair(curExpected,indx)))
            }
            return args.stream()
        }
    }

}

