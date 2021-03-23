package ly.img.awesomebrushapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import ly.img.awesomebrushapplication.decodeSampledBitmapFromResource
import ly.img.awesomebrushapplication.model.Brush
import ly.img.awesomebrushapplication.model.DrawPoint
import ly.img.awesomebrushapplication.model.Stroke
import java.util.*

class BrushCanvas @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    init {
        setWillNotDraw(false)
    }

    var strokeColor = Color.BLUE
    var strokeSize = 10

    private var bitmap: Bitmap? = null
    private var currentStroke: Stroke? = null
    private val drawer = BezierPathDrawer()
    private val queue: Queue<Stroke> = LinkedList()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // start drawing
                currentStroke = Stroke(Brush(strokeColor, strokeSize))
                currentStroke?.addPoint(DrawPoint(event.x, event.y))
            }
            MotionEvent.ACTION_MOVE -> {
                // add point to path
                currentStroke?.addPoint(DrawPoint(event.x, event.y))
            }
            MotionEvent.ACTION_UP -> {
                // finish drawing
                currentStroke?.finished = true
                queue.add(currentStroke)
                currentStroke = null
            }
        }

        // invalidate
        postInvalidate()

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
            currentStroke?.let { drawer.draw(canvas, it) }
            drawer.drawAllStrokes(canvas, queue)
        }
    }

    fun loadImage(uri: Uri) {
        bitmap = decodeSampledBitmapFromResource(context, uri, width, height)
    }
}