package cn.edu.seu.alumni.mvp.presenter.status;

import android.util.Log;

import java.util.List;

import cn.edu.seu.alumni.javabean.EmptyPostBody;
import cn.edu.seu.alumni.javabean.PostStatusRequest;
import cn.edu.seu.alumni.javabean.PostStatusResponse;
import cn.edu.seu.alumni.javabean.QiniuTokenResponse;
import cn.edu.seu.alumni.mvp.model.IService;
import cn.edu.seu.alumni.mvp.model.ServiceProvider;
import cn.edu.seu.alumni.util.QiniuUploadUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by my on 2016/3/3.
 */
public class StatusPresenter implements IStatusPresenter {
    private final String TAG = StatusPresenter.class.getSimpleName();

    @Override
    public void postStatus(List<String> images, final String statusText) {

        final String[] imagePaths = new String[images.size()];
        images.toArray(imagePaths);
        final QiniuUploadUtil.MyUpCompletionHandler myHandler = new QiniuUploadUtil.MyUpCompletionHandler(images.size(), new QiniuUploadUtil.UploadAllCompleteHandler() {
            @Override
            public void complete(List<String> imageHashcodes) {

                String[] images = new String[imageHashcodes.size()];
                imageHashcodes.toArray(images);

                IService service = ServiceProvider.getService();
                service.postStatus(new PostStatusRequest(statusText, images), new Callback<PostStatusResponse>() {
                    @Override
                    public void success(PostStatusResponse postStatusResponse, Response response) {
                        Log.i(TAG, "发布动态成功: " + postStatusResponse.getStatus());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(TAG, error.toString());
                    }
                });
            }
        });

        for (final String imagePath : imagePaths) {
            IService service = ServiceProvider.getService();
            service.getQiniuToken(new EmptyPostBody(), new Callback<QiniuTokenResponse>() {
                @Override
                public void success(QiniuTokenResponse qiniuTokenResponse, Response response) {
                    Log.i(TAG, "获取七牛token成功， token = " + qiniuTokenResponse.getToken() + ", key = " + qiniuTokenResponse.getKey());
                    QiniuUploadUtil.getUploadManager().put(imagePath, qiniuTokenResponse.getKey(), qiniuTokenResponse.getToken(), myHandler, null);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.i(TAG, error.toString());
                }
            });
        }
    }
}
