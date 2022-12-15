package mpdev.aoc2022.utils.annimation

import mpdev.aoc2022.utils.snake.Directions
import java.awt.Rectangle
import java.util.*

class Snake {
    private val BODY_WIDTH = 20
    private val SNAKE_SPEED = BODY_WIDTH
    private val startX = 200
    private val startY = 100
    val body: LinkedList<Rectangle>
    private var direction: Directions

    init {
        direction = Directions.RIGHT
        body = LinkedList()
        body.add(Rectangle(startX, startY + 3 * BODY_WIDTH, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - BODY_WIDTH, startY + 2 * BODY_WIDTH, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 2 * BODY_WIDTH, startY + BODY_WIDTH, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 3 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 4 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 5 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 6 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 7 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 8 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
        body.add(Rectangle(startX - 9 * BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH))
    }

    fun move() {
        val newHead = body.removeLast()
        newHead.x = body.peek().x
        newHead.y = body.peek().y
        when (direction) {
            Directions.UP -> newHead.y -= SNAKE_SPEED
            Directions.DOWN -> newHead.y += SNAKE_SPEED
            Directions.RIGHT -> newHead.x += SNAKE_SPEED
            Directions.LEFT -> newHead.x -= SNAKE_SPEED
        }
        body.addFirst(newHead)
    }
}