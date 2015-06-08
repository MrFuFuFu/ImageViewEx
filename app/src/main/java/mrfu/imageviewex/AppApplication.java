package mrfu.imageviewex;

import android.app.Application;

/**
 * Created by MrFu on 15/6/8.
 */
public class AppApplication extends Application {
    private static AppApplication sInstance;

    public static AppApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
