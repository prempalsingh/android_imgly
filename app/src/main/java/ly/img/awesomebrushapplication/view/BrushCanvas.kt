package ly.img.awesomebrushapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class BrushCanvas @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    init {
        setWillNotDraw(false)
    }

    private val brushStrokePaint = Paint().also {
        it.style = Paint.Style.STROKE
    }
    private val path = Path()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
        // TODO: Implement touch.
    }

    fun updatePath() {
        // The brush should be drawn smooth this means:
        // 1. You have to draw one point in past.
        // 2. The last point must be drawn after leaving your finger
        // 3. If you tap only, a point should be shown.

        // You could use this algorithm.

        data class DummyPosType(
            var x:Float = 0.0f,
            var y:Float = 0.0f
        )

        val isFirstPoint: Boolean = TODO()
        if (isFirstPoint) {
            val point: DummyPosType = TODO("First point")
            path.moveTo(point.x, point.y)
        } else {
            val point: DummyPosType = TODO("Current point to draw. (The point before the last)")
            val lastPoint: DummyPosType = TODO("The point before `point`")
            val nextPoint: DummyPosType = TODO("Next Point to draw (Last you get from onTouchEvent) or null if it is the last")
            val beforeLastPoint: DummyPosType = TODO("The point before lastPoint or lastPoint if there is not point before it.")

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
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // TODO: This is only an example code how drawing in general works.

        val w = width.toFloat()
        val h = height.toFloat()

        brushStrokePaint.color = Color.BLACK
        brushStrokePaint.strokeWidth = 10.0f

        path.reset()
        path.moveTo(w * 0.1f, h * 0.1f)
        path.lineTo(w * 0.9f, h * 0.25f)
        path.lineTo(w * 0.1f, h * 0.5f)
        path.lineTo(w * 0.9f, h * 0.75f)
        path.lineTo(w * 0.1f, h * 0.9f)

        // TODO: If there is time left, try to implement a cache and draw only the last line instead of everything.
        canvas?.drawPath(path, brushStrokePaint)

    }

    companion object {
        private const val SMOOTH_VAL = 3
    }
}