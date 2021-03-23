package ly.img.awesomebrushapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri


fun decodeSampledBitmap(
    context: Context,
    uri: Uri,
    reqWidth: Int,
    reqHeight: Int
): Bitmap {

    val parcelFileDescriptor =
        context.contentResolver.openFileDescriptor(uri, "r")
    val fileDescriptor = parcelFileDescriptor?.fileDescriptor
    // First decode with inJustDecodeBounds=true to check dimensions
    return BitmapFactory.Options().run {
        inJustDecodeBounds = true
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, this)

        // Calculate inSampleSize
        inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        inJustDecodeBounds = false

        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, this)
    }
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    // Raw height and width of image
    val (height: Int, width: Int) = options.run { outHeight to outWidth }
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {

        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}