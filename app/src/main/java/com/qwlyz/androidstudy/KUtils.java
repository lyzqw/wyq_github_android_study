package com.qwlyz.androidstudy;

import com.yuwq.libs_common.BuildConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class KUtils {


    public static void compressFiles(String dir, String zipFile) {
        File directory = new File(dir);
        int rootEndIndex = directory.getAbsolutePath()
                .length() + 1;
        List<String> paths = getPathList(directory);

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String path : paths) {
                // Create a zip entry
                String name = path.substring(rootEndIndex, path.length());
                ZipEntry zipEntry = new ZipEntry(name);
                zos.putNextEntry(zipEntry);
                // Read file content and write to zip output stream
                FileInputStream fis = new FileInputStream(path);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                // Close zip entry and files input stream
                zos.closeEntry();
                fis.close();
            }

            // Close zip output stream and file output stream
            zos.close();
            fos.close();
        } catch (Exception e) { // IOException
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getPathList(File dir) {
        List<String> pathList = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    pathList.add(file.getAbsolutePath());
                }
                // ignore sub dir
            }
        }
        return pathList;
    }
}
