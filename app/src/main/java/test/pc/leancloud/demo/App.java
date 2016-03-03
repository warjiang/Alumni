package test.pc.leancloud.demo;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.controller.ConversationEventHandler;
import com.avoscloud.leanchatlib.utils.ThirdPartUserUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Dell on 2016/3/3.
 */
public class App extends Application {

    private final String TAG = App.class.getSimpleName();

    private final String clientId = "pczhou";

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "fqNPI26vuducBhtSjQGsNfnW-gzGzoHsz", "9ubA50d0guhY4cbAPpDbJDpK");
        ChatManager.setDebugEnabled(true);// tag leanchatlib
        AVOSCloud.setDebugLogEnabled(true);  // set false when release
        initImageLoader(this);

        ThirdPartUserUtils.setThirdPartUserProvider(new CustomUserProvider());

        initChatManager(clientId);
        ChatManager.getInstance().openClient(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    Log.i(TAG, "connected");
                } else {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
                        //.memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void initChatManager(String userId) {
        final ChatManager chatManager = ChatManager.getInstance();
        chatManager.init(this);
        if (!TextUtils.isEmpty(userId)) {
            chatManager.setupManagerWithUserId(this, userId);
        }
        chatManager.setConversationEventHandler(ConversationEventHandler.getInstance());
    }
}
