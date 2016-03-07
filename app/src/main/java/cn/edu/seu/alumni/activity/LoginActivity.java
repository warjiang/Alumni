package cn.edu.seu.alumni.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.application.Constant;
import cn.edu.seu.alumni.mvp.presenter.auth.ILoginPresenter;
import cn.edu.seu.alumni.mvp.presenter.auth.LoginPresenter;
import cn.edu.seu.alumni.mvp.view.auth.ILoginView;

public class LoginActivity extends BaseActivity implements ILoginView{

    private ILoginPresenter iLoginPresenter;

    @Bind(R.id.account_edittext)
    EditText accountEditText;

    @Bind(R.id.password_edittext)
    EditText passwordEditText;

    @Bind(R.id.login_button)
    Button loginButton;

    @Bind(R.id.weixin_login_linearlayout)
    LinearLayout weixinLoginLinearLayout;

    @Bind(R.id.register_linearlayout)
    LinearLayout registerLinearLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    @Override
    protected void initial() {
        iLoginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.login_button)
    public void loginButtonOnClick(){
        iLoginPresenter.loginAlumni(accountEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.register_linearlayout)
    public void registerLinearLayoutOnClick(){
        jumpForResult(RegisterActivity.class, Constant.RequestCode.LOGIN_ACTIVITY);
    }

    @Override
    public void doLoginSucceed() {
        showToast("登录成功");
        jumpThenFinish(MainActivity.class);
    }

    @Override
    public void doLoginFailure(String reason) {
        showToast(reason);
    }
}
