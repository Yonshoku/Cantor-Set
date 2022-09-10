import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class Node(var tree: FracTree, var level: Int, var startPoint: Point? = null, var endPoint: Point? = null) {
    var left: Node? = null
    var right: Node? = null
    var topMargin = 35

    fun initChildren() {
        // Exit if it's last level
        if (level >= tree.levelsNum)
            return

        left = Node(tree, level + 1)
        right = Node(tree, level + 1)

        var length: Double = (endPoint!!.x - startPoint!!.x) / tree.coeff
        var y: Double = startPoint!!.y - tree.gap

        left!!.startPoint = Point(startPoint!!.x, y)
        left!!.endPoint = Point(startPoint!!.x + length, y)

        right!!.startPoint = Point(endPoint!!.x - length, y)
        right!!.endPoint = Point(endPoint!!.x, y)

        left!!.initChildren()
        right!!.initChildren()
    }

    fun draw(fractalCanvas: FractalCanvas, g: Graphics?, certainLevel: Int = -1) {
        // Draw children
        if (level < tree.levelsNum) {
            left!!.draw(fractalCanvas, g, certainLevel)
            right!!.draw(fractalCanvas, g, certainLevel)
        }

        if (certainLevel > 0 && certainLevel != level)
            return

        // Draw itself
        g!!.color = Color((100 * level) % 255, (120 * level) % 255, (80 * level) % 255)
        g!!.drawLine(fractalCanvas.convertX(startPoint!!.x),
            fractalCanvas.convertY(startPoint!!.y),
            fractalCanvas.convertX(endPoint!!.x),
            fractalCanvas.convertY(endPoint!!.y))
    }

    fun drawItself(treeCanvas: TreeCanvas, g: Graphics?, startX: Int, finishX: Int, startY: Int) {
        if (level >= tree.levelsNum)
            return

        left!!.drawItself(treeCanvas, g, startX, startX + ((finishX - startX) / 2) * 1, startY + topMargin)
        right!!.drawItself(treeCanvas, g, startX + ((finishX - startX) / 2) * 1, startX + ((finishX - startX) / 2) * 2, startY + topMargin)

        g!!.drawLine(startX + (finishX - startX) / 2, startY, startX + (finishX - startX) / 4, startY + topMargin)
        g!!.drawLine(startX + (finishX - startX) / 2, startY, startX + ((finishX - startX) / 2) + (finishX - startX) / 4, startY + topMargin)
    }

}