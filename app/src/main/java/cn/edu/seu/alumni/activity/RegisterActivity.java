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

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
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
import cn.edu.seu.alumni.util.DataProvider;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    private IRegisterPresenter iRegisterPresenter;

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

    private OptionsPickerView departmentAndMajorOptionsPickerView;

    private DataProvider.SeuMajors seuMajors;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected void initial() {
        setToolbarTitle(getString(R.string.register));
        iRegisterPresenter = new RegisterPresenter(this);

        seuMajors = DataProvider.getSeuMajorsData();



        departmentAndMajorOptionsPickerView = new OptionsPickerView(this);
        departmentAndMajorOptionsPickerView.setPicker(seuMajors.getDepartments(), seuMajors.getMajors(), true);
        departmentAndMajorOptionsPickerView.setTitle("选择学院/专业");
        departmentAndMajorOptionsPickerView.setCyclic(true, false, false);
        departmentAndMajorOptionsPickerView.setSelectOptions(0, 0);
        departmentAndMajorOptionsPickerView.setCancelable(true);



        departmentAndMajorOptionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String department = seuMajors.getDepartments().get(option1);
                String major = seuMajors.getMajors().get(option1).get(option2);
                departmentTextView.setText(department);
                majorTextView.setText(major);
            }
        });


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
     * 设置学院、专业
     */
    @OnClick({R.id.major_linearlayout,R.id.department_linearlayout})
    public void setDepartmentAndMajor() {
        departmentAndMajorOptionsPickerView.show();
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



    @Override
    public void doRegisterFailure(String reason) {
        showToast(reason);
    }

    @Override
    public void doRegisterSucceed() {
        showToast("注册成功");
        jumpThenFinish(MainActivity.class);
    }




}