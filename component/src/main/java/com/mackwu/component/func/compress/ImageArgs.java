package com.mackwu.component.func.compress;

import android.graphics.BitmapFactory;
import android.util.Pair;

import com.mackwu.component.util.IOUtil;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author MackWu
 * @since 2022/8/25 18:44
 */
public class ImageArgs {

    String path;
    int width;
    int height;
    ImageFormat imageFormat;

    public ImageArgs(String path) {
        this.path = path;
        Pair<Integer, Integer> imageResolution = convertImageResolution(path);
        this.width = imageResolution.first;
        this.height = imageResolution.second;
    }

    Pair<Integer, Integer> convertImageResolution(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            BitmapFactory.decodeStream(inputStream, null, options);
            // SkAndroidCodec::NewFromStream returned null
            try {
                inputStream.reset();
            } catch (Exception ignored) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(inputStream);
        }
        return new Pair<>(options.outWidth, options.outHeight);
    }

//    ImageFormat convertImageFormat(String path) {
//        InputStream inputStream = null;
//        try {
//            File file = new File(path);
//            inputStream = new FileInputStream(file);
//            byte[] bytes = new byte[4];
//            inputStream.read(bytes);
//            String s = ByteUtil.bytesToHex(bytes);
//            return ImageFormatFactory.getImageFormat(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtil.close(inputStream);
//        }
//        return null;
//    }

}
