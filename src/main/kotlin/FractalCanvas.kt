import java.awt.*
import javax.swing.JPanel

class FractalCanvas(private val mWidth: Int, private val mHeight: Int): JPanel() {
    private val max: Double = 900.0

    var canvasColor: Color = Color(255, 255, 255)
    var contourColor: Color = Color(0, 0, 0)
    var shapeColor: Color = Color(0, 0, 0)
    var fracTree: FracTree = FracTree(0.0, 0.0, Point(-800.0, 800.0), Point(800.0, 800.0), 3)
    var treeCanvas: TreeCanvas? = null
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
        g.stroke = BasicStroke(3f)
        g.color = shapeColor

        treeCanvas!!.tree = this.fracTree

        if (certainLevel > 0) {
            fracTree.head.draw(this, g, certainLevel)
            certainLevel = -1
        } else {
            fracTree.head.draw(this, g)
        }

    }

    fun updateLevels(num: Int) {
        fracTree = FracTree(0.0, 0.0, Point(-800.0, 800.0), Point(800.0, 800.0), num)
        treeCanvas!!.updateLevels(fracTree)
        repaint()
    }

    fun drawCertainLevel(y: Int) {
        var num: Int = ((y - 50).toDouble() / 35).toInt()

        if (num > fracTree!!.levelsNum || num < 0)
            return

        this.certainLevel = num + 1
        println(certainLevel)
        repaint()
    }

    fun convertX(x: Double): Int {
        return (((x + max) / (max * 2)) * mWidth).toInt()
    }

    fun convertY(y: Double): Int {
        return (mHeight - ((y + max) / (max * 2)) * mHeight).toInt()
    }
}