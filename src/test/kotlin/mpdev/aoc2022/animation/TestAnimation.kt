package mpdev.aoc2022.animation

import mpdev.aoc2022.utils.annimation.*
import mpdev.aoc2022.utils.snake.SnakeFrame
import org.junit.jupiter.api.*
import java.awt.Color

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("Day 1 Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestAnimation {

    //@Test
    @Order(1)
    fun `Test Snake`() {
        SnakeFrame()
    }

    @Test
    @Order(2)
    fun `Test Animation`() {
        val data1 = listOf(
            AnimationPixel(Pair(5,5)),AnimationPixel(Pair(6,5)),AnimationPixel(Pair(7,5)),AnimationPixel(Pair(8,5)),
            AnimationPixel(Pair(8,6)), AnimationPixel(Pair(8,7), colour = Color.RED)
        )
        val data2 = listOf(
            AnimationPixel(Pair(6,5)),AnimationPixel(Pair(7,5)),AnimationPixel(Pair(8,5)),
            AnimationPixel(Pair(8,6)), AnimationPixel(Pair(8,7)),AnimationPixel(Pair(8,8), colour = Color.RED)
        )
        val data3 = listOf(
            AnimationPixel(Pair(7,5)),AnimationPixel(Pair(8,5)),
            AnimationPixel(Pair(8,6)), AnimationPixel(Pair(8,7)),AnimationPixel(Pair(8,8)),AnimationPixel(Pair(8,9), colour = Color.RED)
        )
        val data4 = listOf(
            AnimationPixel(Pair(8,5)),
            AnimationPixel(Pair(8,6)), AnimationPixel(Pair(8,7)),AnimationPixel(Pair(8,8)),AnimationPixel(Pair(8,9)),
            AnimationPixel(Pair(8,10), SHAPE_SQUARE, Color.RED)
        )
        val data5 = listOf(
            AnimationPixel(Pair(8,6)), AnimationPixel(Pair(8,7)),AnimationPixel(Pair(8,8)),AnimationPixel(Pair(8,9)),
            AnimationPixel(Pair(8,10)),AnimationPixel(Pair(8,11), SHAPE_SQUARE, Color.RED)
        )
        val data6 = listOf(
            AnimationPixel(Pair(8,7)),AnimationPixel(Pair(8,8)),AnimationPixel(Pair(8,9)),
            AnimationPixel(Pair(8,10)),AnimationPixel(Pair(8,11)),AnimationPixel(Pair(8,12), SHAPE_SQUARE, Color.RED)
        )
        val data7 = listOf(
            AnimationPixel(Pair(8,8)),AnimationPixel(Pair(8,9)),
            AnimationPixel(Pair(8,10)),AnimationPixel(Pair(8,11)),AnimationPixel(Pair(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Pair(8,13))
        )
        val data8 = listOf(
            AnimationPixel(Pair(8,9)),
            AnimationPixel(Pair(8,10)),AnimationPixel(Pair(8,11)),AnimationPixel(Pair(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Pair(8,13)),AnimationPixel(Pair(8,14))
        )
        val data9 = listOf(
            AnimationPixel(Pair(8,10)),AnimationPixel(Pair(8,11)),AnimationPixel(Pair(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Pair(8,13)),AnimationPixel(Pair(8,14)), AnimationPixel(Pair(8,15))
        )
        val data10 = listOf(
            AnimationPixel(Pair(8,11)),AnimationPixel(Pair(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Pair(8,13)),AnimationPixel(Pair(8,14)), AnimationPixel(Pair(8,15)), AnimationPixel(Pair(8,16))
        )
        val animationObject = AnimationObject()
        animationObject.items = listOf(
            AnimationItem(listOf()),
            AnimationItem(data1),
            AnimationItem(data2),
            AnimationItem(data3),
            AnimationItem(data4, SHAPE_CIRCLE, Color.CYAN),
            AnimationItem(data5, SHAPE_CIRCLE, Color.CYAN),
            AnimationItem(data6, SHAPE_CIRCLE, Color.CYAN),
            AnimationItem(data7),
            AnimationItem(data8),
            AnimationItem(data9),
            AnimationItem(data10),
        )
        animationObject.timerInterval = 1500
        animationObject.tileSize = 20
        animationObject.gridOn = true
        animationObject.start = Pair(200,200)
        animationObject.rows = 20
        animationObject.columns = 40
        val animationFrame = AnimationFrame(animationObject)
    }
}

