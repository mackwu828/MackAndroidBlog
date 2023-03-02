package com.mackwu.storage;

import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MackWu
 * @since 2023/2/23 19:14
 */
public class StorageUriConverter {

    private static StorageUriConverter instance;
    private final Map<String, Uri> externalUriMap = new HashMap<>();

    private StorageUriConverter() {
    }

    public static StorageUriConverter getInstance() {
        if (instance == null) {
            instance = new StorageUriConverter();
        }
        return instance;
    }

    /**
     * 文件路径转外置存储URI
     * <p>
     * 一、外置存储的URI
     * scheme: content://
     * authority: com.android.externalstorage.documents
     * path: /tree/C8F3-D655:/document:天气图片/weather_bg_unknow.png
     * <p>
     * 二、跳转到系统选择目录com.android.documentsui/.picker.PickActivity，选择sdcard目录后返回
     * <code>
     * Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
     * tartActivityForResult(intent, 0x01);
     * </code>
     * 实际返回：content://com.android.externalstorage.documents/tree/C8F3-D655%3A%E5%A4%A9%E6%B0%94%E5%9B%BE%E7%89%87
     * 遍历文件夹
     * <code>
     * DocumentFile documentFile = DocumentFile.fromTreeUri(this, uri);
     * if (documentFile != null) {
     * DocumentFile[] documentFiles = documentFile.listFiles();
     * for (DocumentFile file : documentFiles) {
     * Uri subUri = file.getUri();
     * }
     * }
     * </code>
     * tree/C8F3-D655%3A/document/C8F3-D655%3A%2Fvideo.mp4
     * /tree/C8F3-D655%3A%E5%A4%A9%E6%B0%94%E5%9B%BE%E7%89%87/document/C8F3-D655%3A%E5%A4%A9%E6%B0%94%E5%9B%BE%E7%89%87%2Fweather_bg_clouds.png
     * /tree/
     * C8F3-D655:天气图片/document/
     * C8F3-D655:天气图片/weather_bg_clouds.png
     *
     * @param path /storage/C8F3-D655/天气图片/weather_bg_unknow.png
     *             /storage/C8F3-D655/video.mp4
     * @return uri
     */
    public Uri pathToExternalUri(String path) {
        try {
            path = path.replace("/storage/", "");
            String[] split = path.split("/");
            String rootPath = split[0];
            String fileName = split[split.length - 1];
            String remainPath = path.replace(rootPath + "/", "").replace((split.length == 2 ? "" : "/") + fileName, "");
            String uri = "content://"
                    + "com.android.externalstorage.documents"
                    + "/tree/"
                    + rootPath + "%3A" + encode(remainPath) + "/document/"
                    + rootPath + "%3A" + encode(remainPath + "/" + fileName);
//            Logger.d("pathToUri...  rootPath=" + rootPath + ", fileName=" + fileName + ", remainPath=" + remainPath + ", uri=" + uri);
            return Uri.parse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void savePathToExternalUri(String fileId, String path) {
        externalUriMap.put(fileId, pathToExternalUri(path));
    }

    public Uri getExternalUri(String fileId) {
        if (externalUriMap.containsKey(fileId)) {
            return externalUriMap.get(fileId);
        }
        return null;
    }

}
