package mpdev.aoc2022.animation

import mpdev.aoc2022.utils.annimation.*
import mpdev.aoc2022.utils.snake.SnakeFrame
import org.junit.jupiter.api.*
import java.awt.Color
import java.awt.Point

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Nested
@DisplayName("AnimationTest")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestAnimation {

    @Test
    @Order(2)
    fun `Test Animation`() {
        val data1 = mutableListOf(
            AnimationPixel(Point(5,5)),AnimationPixel(Point(6,5)),AnimationPixel(Point(7,5)),AnimationPixel(Point(8,5)),
            AnimationPixel(Point(8,6)), AnimationPixel(Point(8,7), colour = Color.RED)
        )
        val data2 = mutableListOf(
            AnimationPixel(Point(6,5)),AnimationPixel(Point(7,5)),AnimationPixel(Point(8,5)),
            AnimationPixel(Point(8,6)), AnimationPixel(Point(8,7)),AnimationPixel(Point(8,8), colour = Color.RED)
        )
        val data3 = mutableListOf(
            AnimationPixel(Point(7,5)),AnimationPixel(Point(8,5)),
            AnimationPixel(Point(8,6)), AnimationPixel(Point(8,7)),AnimationPixel(Point(8,8)),AnimationPixel(Point(8,9), colour = Color.RED)
        )
        val data4 = mutableListOf(
            AnimationPixel(Point(8,5)),
            AnimationPixel(Point(8,6)), AnimationPixel(Point(8,7)),AnimationPixel(Point(8,8)),AnimationPixel(Point(8,9)),
            AnimationPixel(Point(8,10), SHAPE_SQUARE, Color.RED)
        )
        val data5 = mutableListOf(
            AnimationPixel(Point(8,6)), AnimationPixel(Point(8,7)),AnimationPixel(Point(8,8)),AnimationPixel(Point(8,9)),
            AnimationPixel(Point(8,10)),AnimationPixel(Point(8,11), SHAPE_SQUARE, Color.RED)
        )
        val data6 = mutableListOf(
            AnimationPixel(Point(8,7)),AnimationPixel(Point(8,8)),AnimationPixel(Point(8,9)),
            AnimationPixel(Point(8,10)),AnimationPixel(Point(8,11)),AnimationPixel(Point(8,12), SHAPE_SQUARE, Color.RED)
        )
        val data7 = mutableListOf(
            AnimationPixel(Point(8,8)),AnimationPixel(Point(8,9)),
            AnimationPixel(Point(8,10)),AnimationPixel(Point(8,11)),AnimationPixel(Point(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Point(8,13))
        )
        val data8 = mutableListOf(
            AnimationPixel(Point(8,9)),
            AnimationPixel(Point(8,10)),AnimationPixel(Point(8,11)),AnimationPixel(Point(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Point(8,13)),AnimationPixel(Point(8,14))
        )
        val data9 = mutableListOf(
            AnimationPixel(Point(8,10)),AnimationPixel(Point(8,11)),AnimationPixel(Point(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Point(8,13)),AnimationPixel(Point(8,14)), AnimationPixel(Point(8,15))
        )
        val data10 = mutableListOf(
            AnimationPixel(Point(8,11)),AnimationPixel(Point(8,12), SHAPE_SQUARE, Color.RED),
            AnimationPixel(Point(8,13)),AnimationPixel(Point(8,14)), AnimationPixel(Point(8,15)), AnimationPixel(Point(8,16))
        )
        val animationObject = AnimationObject()
        animationObject.items = mutableListOf(
            AnimationItem(data1, colour = Color.GREEN),
            AnimationItem(data2, colour = Color.GREEN),
            AnimationItem(data3, colour = Color.GREEN),
            AnimationItem(data4, SHAPE_CIRCLE, Color.CYAN),
            AnimationItem(data5, SHAPE_CIRCLE, Color.CYAN),
            AnimationItem(data6, SHAPE_CIRCLE, Color.CYAN),
            AnimationItem(data7, colour = Color.GREEN),
            AnimationItem(data8, colour = Color.GREEN),
            AnimationItem(data9, colour = Color.GREEN),
            AnimationItem(data10, colour = Color.GREEN),
        )
        animationObject.timerInterval = 1000
        animationObject.tileSize = 20
        animationObject.gridOn = true
        animationObject.start = Point(200,200)
        animationObject.rows = 20
        animationObject.columns = 40
        val animationFrame = animationObject.start()
    }
}

