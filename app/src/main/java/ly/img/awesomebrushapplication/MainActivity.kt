package ly.img.awesomebrushapplication

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import java.io.OutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*

          == Layout ==

            We need some simple Layout here, use an xml layout or code to create it:
              * Load Image -> Select Image from Gallery.
              * Save Image -> Save Image to Gallery.
              * Color -> A Simply selector where you can select at min. 5 Colors. More Color's or a color-picker is also welcome.
              * Size -> Adjust brush size with a slider
              * Clear all -> Removes the Brush.

          ----------------------------------------------------------------------
         | HINT: The layout doesn't have to look good, but it should be usable. |
          ----------------------------------------------------------------------

          == Requirements ==
            Must have features
              * The result should be drawn in original Size, not in screen size!
                You can ignore OutOfMemory issues here, just use some smaller Image

              *

          == Things to consider ==
            Nice to have features, but keep you code open to implement this later
              * The user can make mistakes, so a history (undo redo) would be nice.
              * The image usually doesn't change while editing, but can be replaced in size.
         */


    }

    fun onPressLoadImage() {
        TODO("load image on background thread.")
        // Adjust Size of the drawable area, after image loading.
    }

    @MainThread
    fun onPressSave() {
        TODO("saveBrushToGallery() on background thread.")
    }

    fun onChangeColor(@ColorInt color:Int) {
        // ColorInt (8bit) Color is ok, do not wast time here.
    }

    fun onSizeChanged(size:Float) {

    }

    @WorkerThread
    fun saveBrushToGallery() {
        // Do not worry about memory there. (do not waste time it's another story)
        // ...simply use only test images that fit's in the memory.

        // Because it can take some time to create the brush, it would be nice to have a progress here.

        val bitmap: Bitmap = TODO("Create in Size of original image, not in Screen size!")
        val outputStream :OutputStream = TODO("Open an ScopedStorage OutputStream and save it in user gallery.")
        outputStream.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        }
    }
}