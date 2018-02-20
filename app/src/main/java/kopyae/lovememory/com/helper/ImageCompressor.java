package kopyae.lovememory.com.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.util.Log;

import java.io.File;

/**
 * Created by ZMN on 6/1/17.
 **/

public class ImageCompressor {

    public static final String TAG = ImageCompressor.class.getSimpleName();


    /**
     * Compress Imgae
     *
     * @param height Height of the output Image Bitmap
     * @param width  Weight of the output Image Bitmap
     * @return Compressed Bitmap
     */


    public static Bitmap compressImage(File targetImage, float height, float width) {

        if (targetImage == null || !targetImage.exists()) {
            return null;
        }

        String filePath = targetImage.getAbsolutePath();

        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

        // by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
        // you try the use the bitmap here, you will get null.

        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

        // max Height and width values of the compressed image is taken as 816x612

        float imgRatio = actualWidth / actualHeight;
        float maxRatio = width / height;

        // width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > height || actualWidth > width) {
            if (imgRatio < maxRatio) {
                imgRatio = height / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) height;
            } else if (imgRatio > maxRatio) {
                imgRatio = width / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) width;
            } else {
                actualHeight = (int) height;
                actualWidth = (int) width;

            }
        }

        //  setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

        //  inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

        // this options allow android to claim the bitmap memory if it runs low on memory

//        options.inPurgeable = true;
//        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
            //  load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        if (scaledBitmap != null) {
            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        }

        // check the rotation of the image and display it properly

        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d(TAG, "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d(TAG, "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d(TAG, "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d(TAG, "Exif: " + orientation);
            }

            if (scaledBitmap == null) {
                return null;
            }

            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);

            return scaledBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * ImageSize Calculation
     *
     * @param options   Bitmap Options
     * @param reqWidth  Required Width
     * @param reqHeight Required Height
     * @return Sample Size
     */

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
}
