class FracTree (x: Double, y: Double, var startPoint: Point, var endPoint: Point, var levelsNum: Int) {
    var coeff: Double = 2.2
    var gap: Double = 45.0
    var head: Node = Node(this, 1, startPoint, endPoint)

    init {
        head.initChildren()
    }
}