package mpdev.aoc2022.utils.annimation

import java.awt.Color

class AnimationObject {
    var start: Pair<Int,Int> = Pair(80,80)
    var rows: Int = 40
    var columns: Int = 40
    var title1: String = "Animation"
    var title2: String = "Demo"
    var tileSize: Int = 8
    var timerInterval = 1000
    var bgndColour = Color(51, 51, 51)
    var gridOn: Boolean = false
    var gridColour: Color = Color.GRAY
    var items: List<AnimationItem> = mutableListOf()
}

const val SHAPE_CIRCLE = 0
const val SHAPE_SQUARE = 1
const val SHAPE_DEFAULT = -1
val COLOUR_DEFAULT = Color.BLACK

class AnimationItem(val data: List<AnimationPixel>, val shape: Int = SHAPE_SQUARE, val colour: Color = Color.GREEN)

class AnimationPixel(val p: Pair<Int,Int>, val shape: Int = SHAPE_DEFAULT, val colour: Color = COLOUR_DEFAULT)