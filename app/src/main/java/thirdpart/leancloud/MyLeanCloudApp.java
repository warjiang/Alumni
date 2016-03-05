package thirdpart.leancloud;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by Dell on 2016/3/3.
 */
public class MyLeanCloudApp extends Application {

    private final String TAG = MyLeanCloudApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "fqNPI26vuducBhtSjQGsNfnW-gzGzoHsz", "9ubA50d0guhY4cbAPpDbJDpK");
    }
}
