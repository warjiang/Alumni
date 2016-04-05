package cn.edu.seu.alumni.mvp.presenter.qiniu;

import android.util.Log;

import cn.edu.seu.alumni.javabean.EmptyPostBody;
import cn.edu.seu.alumni.javabean.QiniuTokenResponse;
import cn.edu.seu.alumni.mvp.model.IService;
import cn.edu.seu.alumni.mvp.model.ServiceProvider;
import cn.edu.seu.alumni.util.Preference;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by 鹏程 on 2016/3/13.
 */
public class QiniuPresenter implements IQiniuPresenter{
    private final String TAG = QiniuPresenter.class.getSimpleName();
    @Override
    public void getQiniuToken() {
        IService service = ServiceProvider.getService();
        service.getQiniuToken(new EmptyPostBody(), new Callback<QiniuTokenResponse>() {
            @Override
            public void success(QiniuTokenResponse qiniuTokenResponse, Response response) {
                Log.i(TAG, "get qiniu token successfully, token = "+qiniuTokenResponse.getToken());
                Preference.putString("qiniu_token", qiniuTokenResponse.getToken());
                Preference.putString("qiniu_key", qiniuTokenResponse.getKey());
                Preference.putBoolean("qiniu_updated", true);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "get qiniu token unsuccessfully");
            }
        });
    }
}
