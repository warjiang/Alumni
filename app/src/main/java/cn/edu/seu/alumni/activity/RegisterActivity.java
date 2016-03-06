package cn.edu.seu.alumni.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;
import cn.edu.seu.alumni.mvp.presenter.auth.IRegisterPresenter;
import cn.edu.seu.alumni.mvp.view.auth.IRegisterView;
import cn.edu.seu.alumni.util.NetUtils;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    private IRegisterPresenter iRegisterPresenter;

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    @Bind(R.id.telephone_number_edittext)
    EditText telephoneNumberEditText;

    @Bind(R.id.password_edittext)
    EditText passwordEditText;

    @Bind(R.id.enroll_year_textview)
    TextView enrollYearTextView;

    @Bind(R.id.department_textview)
    TextView departmentTextView;

    @Bind(R.id.major_textview)
    TextView majorTextView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initial() {
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitle.setText(R.string.register);
    }

    /**
     * 设置入学年份
     */
    @OnClick(R.id.enroll_year_linearlayout)
    public void enrollYearLayoutOnClick(){
        Calendar calendar = Calendar.getInstance();
        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
                .minValue(calendar.get(Calendar.YEAR) - 40)
                .maxValue(calendar.get(Calendar.YEAR) + 5)
                .defaultValue(calendar.get(Calendar.YEAR))
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.BLUE)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();
        new AlertDialog.Builder(this)
                .setTitle("请选择入学年份")
                .setView(numberPicker)
                .setPositiveButton(getString(R.string.ensure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        enrollYearTextView.setText(String.valueOf(numberPicker.getValue()));
                    }
                })
                .show();
    }

    /**
     * 设置学院
     */
    @OnClick(R.id.department_linearlayout)
    public void departmentLinearLayoutOnClick(){

    }

    /**
     * 设置专业
     */
    @OnClick({R.id.major_linearlayout})
    public void majorLinearLayoutOnClick() {

    }

//    /**
//     * 注册按钮
//     */
//    @OnClick(R.id.register_button)
//    void registerButtonOnClick() {
//
//        RegisterAlumniRequest request = new RegisterAlumniRequest();
//        request.setPhone_num(telephoneNumberEditText.getText().toString());
//        request.setPassword(passwordEditText.getText().toString());
//        request.setEnroll_year(enrollYearTextView.getText().toString());
//        request.setSchool(departmentTextView.getText().toString());
//        request.setMajor(majorTextView.getText().toString());
//
//        if (NetUtils.isNetworkConnected(this)) {
//
//            iRegisterPresenter.registerAlumni();
//
//            ApiManager.getService(this).register(req, new Callback<LoginRes>() {
//                @Override
//                public void success(LoginRes loginRes, Response response) {
//                    TLog.i("Register",loginRes.getAccess_token()+" "+loginRes.getUser_id());
//                    saveUserInfo(loginRes);
//                    readyGoThenKill(MainActivity.class);
//                }
//                @Override
//                public void failure(RetrofitError error) {
//                    showInnerError(error);
//                }
//            });
//        }else {
//            showNetWorkError();
//        }
//    }


//    private void saveUserInfo(LoginRes res) {
//        PreferenceUtils.putString(RegisterActivity.this.getApplicationContext(),
//                PreferenceUtils.Key.ACCESS, res.getAccess_token());
//        PreferenceUtils.putString(RegisterActivity.this.getApplicationContext(),
//                PreferenceUtils.Key.USER_ID, res.getUser_id());
//    }

    @Override
    public void doRegisterFailure(String reason) {

    }

    @Override
    public void doRegisterSucceed() {

    }
}