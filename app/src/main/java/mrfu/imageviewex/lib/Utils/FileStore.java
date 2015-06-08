package mrfu.imageviewex.lib.Utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

import mrfu.imageviewex.AppApplication;

/**
 * Created by MrFu on 15/6/8.
 */
public class FileStore {

    public static Context context = AppApplication.getInstance();
    private static String PATH = "/mrfu/picture/";
    private static String PATH_CACHE = "/mrfu/cache/";



    /**
     *
     * @param httpUrl
     * @return
     */
    public synchronized static String cachePathForKey(String httpUrl) {
        if (!TextUtils.isEmpty(httpUrl)) {
            if (httpUrl.startsWith("http")) {
                String md5 = MD5Util.md5(httpUrl);
                boolean sdCardExist = Environment.getExternalStorageState()
                        .equals(android.os.Environment.MEDIA_MOUNTED);
                String pathName = "";
                if (sdCardExist) {
                    String path = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + PATH;
                    pathName = path + md5;
                    File file = new File(pathName);
                    if (file.exists()) {
                        return pathName;
                    }

                    path = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + PATH_CACHE;
                    pathName = path + md5;
                    file = new File(pathName);
                    if (file.exists()) {
                        return pathName;
                    }
                }

                String path = context.getCacheDir().getAbsolutePath()+ PATH;
                pathName = path + md5;
                File file = new File(pathName);
                if (file.exists()) {
                    return pathName;
                }

                path = context.getCacheDir().getAbsolutePath() + PATH_CACHE;
                pathName = path + md5;
                file = new File(pathName);
                if (file.exists()) {
                    return pathName;
                }
            }
        }
        return "";
    }

    public static String createNewCacheFile(String fileName, boolean addTmp) {
        synchronized (context) {
            boolean sdCardExist = Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED);
            String path = "";
            if (sdCardExist) {
                path = Environment.getExternalStorageDirectory().getAbsolutePath() + PATH;
            } else {
                path = context.getCacheDir().getAbsolutePath() + PATH;
            }
            File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
                File noScanFile = new File(path + ".nomedia");
                try {
                    noScanFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    clearFile();
                }
            }
            String name = MD5Util.md5(fileName);
            String pathName = path + name;
            if(addTmp) {
                pathName += ".tmp";
            }
            File file = new File(pathName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return pathName;
        }
    }

    private static void clearFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                File cacheDir = AppApplication.getInstance().getCacheDir();
                deleteFiles(cacheDir);
                boolean sdCardExist = Environment.getExternalStorageState()
                        .equals(android.os.Environment.MEDIA_MOUNTED);
                if (sdCardExist) {
                    try {
                        deleteFiles(new File(Environment
                                .getExternalStorageDirectory()
                                .getAbsolutePath()
                                + PATH));
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    // delete files
    public static void deleteFiles(File file) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFiles(files[i]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
