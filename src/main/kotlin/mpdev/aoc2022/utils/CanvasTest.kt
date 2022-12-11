package mpdev.aoc2022.utils

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import kotlin.concurrent.timer
import kotlin.system.exitProcess


class AwtControlDemo(var title: String, var width: Int, var height: Int, var rows: Int, var cols: Int) {

    private val mainFrame = Frame(title)
    private val headerLabel = Label()
    private val statusLabel = Label()
    private val controlPanel = Panel()

    init {
        mainFrame.setSize(width, height)
        mainFrame.layout = GridLayout(rows, cols)
        mainFrame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(windowEvent: WindowEvent) {
                exitProcess(0)
            }
        })
        headerLabel.alignment = Label.CENTER
        statusLabel.alignment = Label.CENTER
        statusLabel.setSize(800, 800)
        controlPanel.layout = FlowLayout()
        //mainFrame.add(headerLabel)
        mainFrame.add(controlPanel)
        //mainFrame.add(statusLabel)
        mainFrame.isVisible = true
    }

    private fun showCanvasDemo() {
        headerLabel.text = title
        controlPanel.add(MyCanvas())
        mainFrame.isVisible = true
    }

    internal inner class MyCanvas : Canvas() {
        init {
            background = Color.YELLOW
            setSize(800, 800)
        }
        override fun paint(g: Graphics) {
            val g2: Graphics2D = g as Graphics2D
            g2.drawString("It is a custom canvas area", 0, 0)
            for (i in 1..9)
                g2.drawLine(0,i*80,800,i*80)
            for (i in 1..9)
                g2.drawLine(i*80,0,i*80,800)
            g2.drawRect(80,80,160,160)

        }
    }

    fun testCanvas() {
        showCanvasDemo()
        Thread.sleep(4000)
    }
}