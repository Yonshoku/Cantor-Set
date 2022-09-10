import java.awt.BorderLayout
import java.awt.Color
import java.awt.FlowLayout
import java.awt.Font
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class TreeFrame(): JFrame() {
    private val frameWidth = 900
    private val frameHeight = 900
    private val canvasWidth = 900
    private val canvasHeight = 900

    val canvas = TreeCanvas(canvasWidth, canvasHeight)
    var fractalCanvas: FractalCanvas? = null
    private val canvasBox = Box(BoxLayout.Y_AXIS)

    private val buttonsPanel = JPanel(FlowLayout(FlowLayout.CENTER))
    init {
        initFrame()

        // Frame
        contentPane.layout = BorderLayout()

        // Canvas
        canvasBox.add(Box.createVerticalGlue())
        canvasBox.add(canvas)
        canvasBox.add(Box.createVerticalGlue())

        contentPane.add(canvasBox, BorderLayout.PAGE_START)
        contentPane.add(buttonsPanel, BorderLayout.PAGE_END)

        isResizable = false
        isVisible = true

        addMouseListener(object: MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                fractalCanvas!!.drawCertainLevel(e!!.y)
                println("Clicked ${e.y}")
            }
        })
    }

    fun initFrame() {
        title = "Tree"
        setSize(frameWidth, frameHeight)

        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)
    }
}