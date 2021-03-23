package ly.img.awesomebrushapplication.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import ly.img.awesomebrushapplication.model.Stroke
import java.util.*

class BezierPathDrawer {

    companion object {
        private const val SMOOTH_VAL = 3
    }

    private lateinit var path: Path
    private lateinit var paint: Paint
    private var currentStrokeId = ""

    fun draw(canvas: Canvas, stroke: Stroke) {

        if (currentStrokeId != stroke.id) {
            // initialise path and paint
            path = Path()
            paint = Paint().apply {
                strokeWidth = stroke.brush.size.toFloat()
                color = stroke.brush.color
                style = Paint.Style.STROKE
            }

            currentStrokeId = stroke.id
        }

        if (stroke.getPath().size == 1 && stroke.finished) {
            //TODO: draw single point (circle)
            return
        }

        path.moveTo(stroke.getPath()[0].x, stroke.getPath()[0].y)

        for (i in 1 until stroke.getPath().size) {
            val point = stroke.getPath()[i]
            val lastPoint = stroke.getPath()[i - 1]
            val nextPoint = stroke.getPath().getOrNull(i + 1)
            val beforeLastPoint = stroke.getPath().getOrNull(i - 2) ?: lastPoint

            val pointDx: Float
            val pointDy: Float
            if (nextPoint == null) {
                pointDx = (point.x - lastPoint.x) / SMOOTH_VAL
                pointDy = (point.y - lastPoint.y) / SMOOTH_VAL
            } else {
                pointDx = (nextPoint.x - lastPoint.x) / SMOOTH_VAL
                pointDy = (nextPoint.y - lastPoint.y) / SMOOTH_VAL
            }

            val lastPointDx = (point.x - beforeLastPoint.x) / SMOOTH_VAL
            val lastPointDy = (point.y - beforeLastPoint.y) / SMOOTH_VAL

            path.cubicTo(
                lastPoint.x + lastPointDx,
                lastPoint.y + lastPointDy,
                point.x - pointDx,
                point.y - pointDy,
                point.x,
                point.y
            )

            canvas.drawPath(path, paint)
        }

    }

    fun drawAllStrokes(canvas: Canvas, queue: Queue<Stroke>) {
        queue.forEach {
            draw(canvas, it)
        }
    }

}