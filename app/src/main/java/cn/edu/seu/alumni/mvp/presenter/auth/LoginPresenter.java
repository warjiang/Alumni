package cn.edu.seu.alumni.mvp.presenter.auth;

import android.os.Handler;
import android.os.Looper;


import cn.edu.seu.alumni.constant.Overall;
import cn.edu.seu.alumni.javabean.http.AuthResponse;
import cn.edu.seu.alumni.javabean.http.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeixinRequest;
import cn.edu.seu.alumni.mvp.model.IService;
import cn.edu.seu.alumni.mvp.model.ServiceProvider;
import cn.edu.seu.alumni.mvp.view.auth.ILoginView;
import cn.edu.seu.alumni.util.CommonUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    private Handler handler;

    private static final int LOGIN_ALUMNI = 0;
    private static final int LOGIN_WEIBO = 1;
    private static final int LOGIN_WEIXIN= 2;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.handler = new Handler(Looper.getMainLooper());
    }

    private void login(int loginType, String param1, String param2){

        IService service = ServiceProvider.getService(Overall.applicationContext);
        Callback<AuthResponse> callback = new Callback<AuthResponse>() {
            @Override
            public void success(AuthResponse authResponse, Response response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.doLoginSucceed();
                    }
                });
            }

            @Override
            public void failure(final RetrofitError error) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.doLoginFailure(CommonUtils.getErrorReason(error));
                    }
                });
            }
        };
        switch(loginType){
            case LOGIN_ALUMNI:
                service.loginAlumni(new LoginAlumniRequest(param1, param2), callback);
                break;
            case LOGIN_WEIBO:
                service.loginWeibo(new LoginWeiboRequest(param1, param2), callback);
                break;
            case LOGIN_WEIXIN:
                service.loginWeixin(new LoginWeixinRequest(param1, param2), callback);
                break;
        }
    }


    @Override
    public void loginAlumni(String phone_num, String password) {
        login(LOGIN_ALUMNI, phone_num, password);
    }

    @Override
    public void loginWeibo(String uid, String access_token) {
        login(LOGIN_WEIBO, uid, access_token);
    }

    @Override
    public void loginWeixin(String open_id, String access_token) {
        login(LOGIN_WEIXIN, open_id, access_token);
    }
}
