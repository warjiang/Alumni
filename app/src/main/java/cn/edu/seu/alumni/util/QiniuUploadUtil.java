package cn.edu.seu.alumni.util;

import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 鹏程 on 2016/3/13.
 */
public class QiniuUploadUtil {

    public interface UploadAllCompleteHandler {
        void complete(List<String> imageHashcodes);
    }

    private static UploadManager uploadManager = new UploadManager();

    private static class MyUpCompletionHandler implements UpCompletionHandler{
        private int total;
        private Integer count = 0;
        private boolean failure = false;
        private UploadAllCompleteHandler handler;
        private List<String> hashcodes = new ArrayList<>();
        public MyUpCompletionHandler(int total, UploadAllCompleteHandler handler) {
            this.total = total;
            this.handler = handler;
        }
        @Override
        public void complete(String hashcode, ResponseInfo responseInfo, JSONObject jsonObject) {

            if(failure || !responseInfo.isOK()){
                failure = true;
                return;
            }
            hashcodes.add(hashcode);
            synchronized (count){
                count += 1;
                if(count == total){
                    handler.complete(hashcodes);
                }
            }

        }
    }

    //传入图片数据，返回图片的hashcode，支持单张或多张图片
    public static void upload(final List<byte[]> imageDatas, UploadAllCompleteHandler handler){
        if(null==imageDatas||imageDatas.isEmpty()){
            handler.complete(null);
            return;
        }
        MyUpCompletionHandler myHandler = new MyUpCompletionHandler(imageDatas.size(), handler);
        for(byte[] imagedata : imageDatas){
            uploadManager.put(imagedata, null, Preference.getString("qiniu_token", ""), myHandler, null);
            //uploadManager.put(imagedata, null, "b2cjWlox-DWUzF_7iGA2I1BAlD9lONBTUMY22wxu:4InDyn0aPXL73Gr8-zHxXrIsJbM=:eyJzY29wZSI6Imltb29jIiwiZGVhZGxpbmUiOjE0NTc4OTIxMjh9", myHandler, null);
        }

    }

}
