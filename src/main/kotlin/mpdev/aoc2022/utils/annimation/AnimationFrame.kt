package mpdev.aoc2022.utils.annimation

import java.awt.Color
import javax.swing.JFrame
import javax.swing.JLabel


class AnimationFrame(animationObject: AnimationObject) : JFrame() {
    private val animationPanel: AnimationPanel
    private val subTitle: JLabel

    init {
        title = animationObject.title1
        val width = animationObject.columns * animationObject.tileSize
        val height = animationObject.rows * animationObject.tileSize
        this.setBounds(animationObject.start.x, animationObject.start.y, width+40+1, height+75+1)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        this.isResizable = false
        contentPane.layout = null
        contentPane.background = Color.BLACK
        subTitle = JLabel(animationObject.title2)
        subTitle.setBounds(0, 0, width/2, 20)
        subTitle.foreground = Color.WHITE
        contentPane.add(subTitle)
        animationPanel = AnimationPanel(animationObject)
        animationPanel.setBounds(20, 20, width+1, height+1)
        this.add(animationPanel)
        this.isVisible = true
        // loop until exit key is pressed
        while (animationPanel.animationManager.isRunning) Thread.sleep(100)
    }

}

