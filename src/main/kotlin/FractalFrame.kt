import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*

class FractalFrame: JFrame() {
    private val frameWidth = 1050
    private val frameHeight = 1050
    private val canvasWidth = 900
    private val canvasHeight = 900

    private val fractalCanvas = FractalCanvas(canvasWidth, canvasHeight)
    private val canvasBox = Box(BoxLayout.Y_AXIS)

    private val textField = JTextField()
    private val button = JButton("Update")

    private val buttonsPanel = JPanel(FlowLayout(FlowLayout.CENTER))
    init {
        initFrame()

        // Frame
        contentPane.layout = BorderLayout()

        // Canvas
        canvasBox.add(Box.createVerticalGlue())
        canvasBox.add(fractalCanvas)
        canvasBox.add(Box.createVerticalGlue())

        textField.preferredSize = Dimension(100, 20)
        buttonsPanel.add(textField)
        buttonsPanel.add(button)

        contentPane.add(canvasBox, BorderLayout.PAGE_START)
        contentPane.add(buttonsPanel, BorderLayout.PAGE_END)

        button.addActionListener {
            fractalCanvas.updateLevels(textField.text.toInt())
        }

        isResizable = false
        isVisible = true

        var treeFrame = TreeFrame()
        fractalCanvas.treeCanvas = treeFrame.canvas
        treeFrame.fractalCanvas = fractalCanvas

    }

    fun initFrame() {
        title = "Fractal"
        setSize(frameWidth, frameHeight)

        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)
    }
}