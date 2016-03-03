package test.pc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.edu.seu.alumni.R;

public class MyLeanCloudDemoMainActivity extends Activity {

    private final String TAG = MyLeanCloudDemoMainActivity.class.getSimpleName();
    private AVIMClient imClient;

    class MyMessageHandler extends AVIMMessageHandler {
        @Override
        public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
            Log.i(TAG, "Msg recieved: " + message.getContent());
            TextView textView = (TextView)findViewById(R.id.lean_cloud_demo_text_view);
            textView.setText(message.getContent());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lean_cloud_demo_main);

        AVIMMessageManager.registerDefaultMessageHandler(new MyMessageHandler());

        send();
    }

    private void send(){
        imClient = AVIMClient.getInstance("pczhou");
        imClient.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null != e) {
                    e.printStackTrace();
                } else {
                    Log.i(TAG, "connected");

                    ArrayList<String> clientIds = new ArrayList<String>();
                    clientIds.add("pczhou");
                    clientIds.add("dwj");
                    Map<String, Object> attrs = new HashMap<String, Object>();
                    attrs.put("type", "one_one");

                    imClient.createConversation(clientIds, attrs, new AVIMConversationCreatedCallback() {
                        @Override
                        public void done(AVIMConversation avimConversation, AVIMException e) {
                            if (null != e) {
                                e.printStackTrace();
                            } else {
                                AVIMMessage msg = new AVIMMessage();
                                msg.setContent("Hello!");
                                avimConversation.sendMessage(msg, new AVIMConversationCallback() {
                                    @Override
                                    public void done(AVIMException e) {
                                        if (null != e) {
                                            e.printStackTrace();
                                        } else {
                                            Log.i(TAG, "Send succesfully");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=imClient){
            imClient.close(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    if(null!=e){
                        e.printStackTrace();
                    }else{
                        Log.i(TAG, "disconnected");
                    }
                }
            });
        }
    }
}
