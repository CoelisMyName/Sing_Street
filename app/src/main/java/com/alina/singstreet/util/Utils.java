package com.alina.singstreet.util;

import android.content.Context;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Utils {
    static String getAbsolutePath(Context context, String date) {
        String dirPath = context.getExternalCacheDir().getAbsolutePath();
        return dirPath + "/" + date + ".3gpp";
    }

    static URI absolutePathToUri(String path) {
        File file = new File(path);
        URI uri;
        return file.toURI();
    }

    static String getTimestamp() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}
