# img.ly - Android Coding Challenge
The goal of this challenge is to create a Drawing Application in native Android, which allows a user to annotate images by brushing on it. 
Feel free to use good old Java or Kotlin, if useful you can also use c++ for some high performance methods.

## Layout
### The layout doesn't have to look good, but it should be usable.
We need some simple Layout here, use an xml layout or code to create it:
  * Load Image -> Select Image from Gallery.
  * Save Image -> Save Image to Gallery.
  * Color -> A Simply selector where you can select at min. 5 Colors. More Color's or a color-picker is also welcome.
  * Size -> Adjust brush size with a slider
  * Clear all -> Removes the Brush.


# Requirements 
Must have features
  * The result should be drawn in original Size, not in screen size!
    You can ignore OutOfMemory issues here. If you have problems just use some smaller image for testing.

# Things to consider
Nice to have features, but keep your code open to implement this later
  * The user can make mistakes, so a history (undo redo) would be nice.
  * The image usually doesn't change while brushing, but can be replaced in size. (Small preview, highres rendering)
