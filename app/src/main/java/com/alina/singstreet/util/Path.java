package com.alina.singstreet.util;

import android.content.Context;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Path {
    static String getAbsolutePath(Context context) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        String dirPath = context.getExternalCacheDir().getAbsolutePath();
        return dirPath + "/" + date + ".3gpp";
    }

    static URI absolutePathToUri(String path) {
        File file = new File(path);
        URI uri;
        return file.toURI();
    }

}
