package ly.img.awesomebrushapplication.model

import java.util.*

data class Stroke(
    val brush: Brush,
    val id: String = UUID.randomUUID().toString()
) {
    private val points = arrayListOf<DrawPoint>()
    var finished = false

    fun addPoint(point: DrawPoint) {
        points.add(point)
    }

    fun getPath(): List<DrawPoint> = points

}

