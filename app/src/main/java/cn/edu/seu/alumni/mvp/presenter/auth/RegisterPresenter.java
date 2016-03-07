package cn.edu.seu.alumni.mvp.presenter.auth;

import android.os.Handler;
import android.os.Looper;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.application.App;
import cn.edu.seu.alumni.javabean.http.AuthResponse;
import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;
import cn.edu.seu.alumni.mvp.model.IService;
import cn.edu.seu.alumni.mvp.model.ServiceProvider;
import cn.edu.seu.alumni.mvp.view.auth.IRegisterView;
import cn.edu.seu.alumni.util.CommonUtil;
import cn.edu.seu.alumni.util.InputChecker;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterPresenter implements  IRegisterPresenter{

    private IRegisterView iRegisterView;
    private Handler handler;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void registerAlumni(RegisterAlumniRequest registerAlumniRequest) {

        final InputChecker.CheckResult checkResult = InputChecker.check(registerAlumniRequest);
        if(!checkResult.isLegal()){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iRegisterView.doRegisterFailure(checkResult.getErrorReason());
                }
            });
            return;
        }

        if(!CommonUtil.Network.isConnected(App.getContext())){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iRegisterView.doRegisterFailure(App.getContext().getString(R.string.error_reason_network));
                }
            });
            return;
        }

        IService service = ServiceProvider.getService(App.getContext());
        service.registerAlumni(registerAlumniRequest, new Callback<AuthResponse>() {
            @Override
            public void success(AuthResponse authResponse, Response response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iRegisterView.doRegisterSucceed();
                    }
                });
            }

            @Override
            public void failure(final RetrofitError error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iRegisterView.doRegisterFailure(CommonUtil.Retrofit.getErrorReason(error));
                    }
                });
            }
        });
    }
}
