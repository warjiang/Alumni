package cn.edu.seu.alumni.mvp.presenter.auth;

import cn.edu.seu.alumni.constant.Overall;
import cn.edu.seu.alumni.javabean.http.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.http.LoginAlumniResponse;
import cn.edu.seu.alumni.javabean.http.LoginWeiboOrWeixinResponse;
import cn.edu.seu.alumni.javabean.http.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeixinRequest;
import cn.edu.seu.alumni.mvp.model.ServiceProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginPresenter implements ILoginPresenter {

    @Override
    public void loginAlumni(String phone_num, String password) {
        ServiceProvider.getService(Overall.applicationContext).loginAlumni(new LoginAlumniRequest(phone_num, password),
                new Callback<LoginAlumniResponse>() {
                    @Override
                    public void success(LoginAlumniResponse loginAlumniResponse, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    @Override
    public void loginWeibo(String uid, String access_token) {
        ServiceProvider.getService(Overall.applicationContext).loginWeibo(new LoginWeiboRequest(uid, access_token),
                new Callback<LoginWeiboOrWeixinResponse>() {
                    @Override
                    public void success(LoginWeiboOrWeixinResponse loginWeiboOrWeixinResponse, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    @Override
    public void loginWeixin(String open_id, String access_token) {
        ServiceProvider.getService(Overall.applicationContext).loginWeixin(new LoginWeixinRequest(open_id, access_token),
                new Callback<LoginWeiboOrWeixinResponse>() {
                    @Override
                    public void success(LoginWeiboOrWeixinResponse loginWeiboOrWeixinResponse, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }
}
