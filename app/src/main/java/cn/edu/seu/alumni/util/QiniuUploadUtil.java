package cn.edu.seu.alumni.util;

import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.edu.seu.alumni.mvp.presenter.qiniu.QiniuPresenter;

/**
 * Created by 鹏程 on 2016/3/13.
 */
public class QiniuUploadUtil {

    public interface UploadAllCompleteHandler {
        void complete(List<String> imageHashcodes);
    }

    private static UploadManager uploadManager;

    public static UploadManager getUploadManager(){
        if(null == uploadManager){
            uploadManager = new UploadManager();
        }
        return uploadManager;
    }

    public static class MyUpCompletionHandler implements UpCompletionHandler{
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
                Log.i("MyUpCompletionHandler", responseInfo.toString());
                return;
            }
            Log.i("MyUpCompletionHandler", "hashcode = "+hashcode);
            hashcodes.add(hashcode);
            synchronized (this){
                count += 1;
                if(count == total){
                    Log.i("MyUpCompletionHandler", "all images upload complete");
                    handler.complete(hashcodes);
                }
            }
        }
    }

}
