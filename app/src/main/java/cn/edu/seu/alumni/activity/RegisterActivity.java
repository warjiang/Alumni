package cn.edu.seu.alumni.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;
import cn.edu.seu.alumni.mvp.presenter.auth.IRegisterPresenter;
import cn.edu.seu.alumni.mvp.presenter.auth.RegisterPresenter;
import cn.edu.seu.alumni.mvp.view.auth.IRegisterView;

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

    @Bind(R.id.btn_activity_qrcode_decode)
    Button btn_activity_qrcode_decode;
    @Bind(R.id.btn_activity_qrcode_generate)
    Button btn_activity_qrcode_generate;
    @Bind(R.id.btn_activity_qrcode_scan)
    Button btn_activity_qrcode_scan;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initial() {
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitle.setText(R.string.register);
        iRegisterPresenter = new RegisterPresenter(this);
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

    /**
     * 注册按钮
     */
    @OnClick(R.id.register_button)
    void registerButtonOnClick() {
        iRegisterPresenter.registerAlumni(new RegisterAlumniRequest(telephoneNumberEditText.getText().toString(),
                enrollYearTextView.getText().toString(), departmentTextView.getText().toString(),
                majorTextView.getText().toString(), passwordEditText.getText().toString()));
    }

    @OnClick(R.id.btn_activity_qrcode_scan)
    void scanQRCode(){

        Log.d("aa","scanQRCode");
        startActivity(new Intent(this,ScanActivity.class));
    }
    @OnClick(R.id.btn_activity_qrcode_generate)
    void generateQRCode(){

        Log.d("aa","generateQRCode");
        startActivity(new Intent(this,GenerateActivity.class));
    }
    @OnClick(R.id.btn_activity_qrcode_decode)
    void decodeQRCode(){

        Log.d("aa","decodeQRCode");
        startActivity(new Intent(this,DecodeActivity.class));
    }

    @Override
    public void doRegisterFailure(String reason) {
        showToast("注册失败");
    }

    @Override
    public void doRegisterSucceed() {
        showToast("注册成功");
        jumpThenFinish(MainActivity.class);
    }

    @Bind(R.id.tmp)
    Button tmp;
    @OnClick((R.id.tmp))
    void tmpButtonOnClick(){
        jumpThenFinish(MainActivity.class);
    }
}