import java.awt.*
import javax.swing.JPanel

class TreeCanvas(private val mWidth: Int, private val mHeight: Int): JPanel() {
    private val max: Double = 900.0

    var canvasColor: Color = Color(255, 255, 255)
    var contourColor: Color = Color(0, 0, 0)
    var shapeColor: Color = Color(0, 0, 0)
    var tree: FracTree? = null
    var certainLevel: Int = -1

    init {
        preferredSize = Dimension(mWidth, mHeight)
        minimumSize = Dimension(mWidth, mHeight)
        maximumSize = Dimension(mWidth, mHeight)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        background = canvasColor

        (g as Graphics2D).color = contourColor
        g.stroke = BasicStroke(1f)
        g.color = shapeColor

        tree!!.head.drawItself(this, g, 0, 900, 20)
    }

    fun updateLevels(tree: FracTree) {
        this.tree = tree
        repaint()
    }

}