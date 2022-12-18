package mpdev.aoc2022.utils.annimation

import java.awt.Color
import java.awt.Point

class AnimationObject {
    var start: Point = Point(80,80)
    var rows: Int = 40
    var columns: Int = 40
    var title1: String = "Animation"
    var title2: String = "Demo"
    var tileSize: Int = 8
    var timerInterval = 1000
    var bgndColour = Color(51, 51, 51)
    var gridOn: Boolean = false
    var gridColour: Color = Color.GRAY
    var items: MutableList<AnimationItem> = mutableListOf()

    init {
        addItem()
    }

    fun addItem() {
        println("adding empty item to AnimationItems")
        items.add(AnimationItem(mutableListOf()))
    }

    fun copyLastItem() {
        val lastItem = items.last()
        items.add(AnimationItem(mutableListOf(), lastItem.shape, lastItem.colour))
        lastItem.data.forEach {  items.last().data.add(AnimationPixel(Point(it.p.x,it.p.y), it.shape, it.colour)) }
        println("adding last item once more to AnimationItems: $lastItem")
    }

    fun addPixel(p: Point, shape: Int = SHAPE_DEFAULT, colour: Color = COLOUR_DEFAULT) {
        items.last().data.add(AnimationPixel(p, shape, colour))
    }

    fun removeLastPixel() {
        items.last().data.removeLast()
    }

    fun start() = AnimationFrame(this)
}

const val SHAPE_CIRCLE = 0
const val SHAPE_SQUARE = 1
const val SHAPE_DEFAULT = -1
val COLOUR_DEFAULT = Color.BLACK

class AnimationItem(val data: MutableList<AnimationPixel>, val shape: Int = SHAPE_SQUARE,
                    val colour: Color = Color(30, 30,30)) {
    override fun toString() = "$data shape: $shape colour: $colour"
}

class AnimationPixel(val p: Point, val shape: Int = SHAPE_DEFAULT,
                     val colour: Color = COLOUR_DEFAULT) {
    override fun toString() = "$p shape: $shape colour: $colour"
}