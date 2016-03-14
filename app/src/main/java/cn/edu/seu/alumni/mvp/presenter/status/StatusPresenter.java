package cn.edu.seu.alumni.mvp.presenter.status;

import android.util.Log;

import java.util.List;

import cn.edu.seu.alumni.javabean.PostStatusRequest;
import cn.edu.seu.alumni.javabean.PostStatusResponse;
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
    public void postStatus(List<byte[]> imageDatas, final String statusText) {

        QiniuUploadUtil.upload(imageDatas, new QiniuUploadUtil.UploadAllCompleteHandler() {
            @Override
            public void complete(List<String> imageHashcodes) {
                String[] image = null;
                if(null!=imageHashcodes&&!imageHashcodes.isEmpty()){
                    image = (String[])(imageHashcodes.toArray());
                }
                IService service = ServiceProvider.getService();
                service.postStatus(new PostStatusRequest(statusText, image),
                        new Callback<PostStatusResponse>() {
                            @Override
                            public void success(PostStatusResponse postStatusResponse, Response response) {
                                Log.i(TAG, "post status successfully");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.i(TAG, "post status unsuccessfully");
                            }
                        });
            }
        });
    }
}
