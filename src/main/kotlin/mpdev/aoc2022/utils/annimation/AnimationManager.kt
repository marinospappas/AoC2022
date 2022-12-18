package mpdev.aoc2022.utils.annimation

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.time.LocalTime


class AnimationManager(private val animationPanel: AnimationPanel, animationObject: AnimationObject) {
    private val objColor: Color
    private val objColorHead: Color
    private val objColorTail: Color
    private var animationObject: AnimationObject
    private var animationObjIndx = 0
    val isRunning: Boolean

    companion object {
        var firstRender = true
    }

    init {
        println("${LocalTime.now()} animation Manager created")
        this.animationObject = animationObject
        objColor = Color.GREEN
        objColorHead = Color.RED
        objColorTail = Color.BLUE
        isRunning = true
    }

    val tileWidth = animationObject.tileSize
    val tileHeight = animationObject.tileSize

    fun renderObject(graphics: Graphics) {
        if (firstRender) {  // skip first time as this fun is called twice in the beginning
            firstRender = false
            return
        }
        println("${LocalTime.now()} animation Manager : renderObject called")
        println("${LocalTime.now()} animation index $animationObjIndx")
        val graphics2D = graphics as Graphics2D
        if (animationObject.gridOn)
            drawGrid(graphics2D, animationObject)
        if (animationObject.items.isEmpty())
            return
        val animationItem = animationObject.items[animationObjIndx]
        println("${LocalTime.now()} points: ${animationItem.data}")
        if (animationObjIndx < animationObject.items.size-1)
            ++animationObjIndx
        for (i in 0 until animationItem.data.size) {
            val pixel = animationItem.data[i]
            val width = tileWidth
            val height = tileHeight
            if (pixel.colour == COLOUR_DEFAULT)
                graphics2D.color = animationItem.colour
            else
                graphics2D.color = pixel.colour
            val x = pixel.p.x * tileWidth
            val y = pixel.p.y * tileHeight
            if (pixel.shape == SHAPE_DEFAULT) {
                if (animationItem.shape == SHAPE_SQUARE)
                    graphics2D.fillRect(x, y, width, height)
                else
                    graphics2D.fillOval(x, y, width, height)
            }
            else {
                if (pixel.shape == SHAPE_SQUARE)
                    graphics2D.fillRect(x, y, width, height)
                else
                    graphics2D.fillOval(x, y, width, height)
            }
        }
    }

    fun drawGrid(g: Graphics2D, animationObject: AnimationObject) {
        g.color = animationObject.gridColour
        val width = animationObject.columns * animationObject.tileSize
        val height = animationObject.rows * animationObject.tileSize
        for (i in 0 until animationObject.rows)
            g.drawLine(0,i*animationObject.tileSize, width, i*animationObject.tileSize)
        for (i in 0 until animationObject.columns)
            g.drawLine(i*animationObject.tileSize, 0, i*animationObject.tileSize, height)
        g.drawLine(0, height, width, height)
        g.drawLine(width, 0, width, height)

    }

    fun draw() {
        println("${LocalTime.now()} animation Manager : draw called repaint")
        animationPanel.repaint()
    }

}

