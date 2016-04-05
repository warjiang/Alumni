package cn.edu.seu.alumni.activity.other;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.BaseActivity;
import cn.edu.seu.alumni.javabean.auth.RegisterAlumniRequest;
import cn.edu.seu.alumni.mvp.presenter.auth.IRegisterPresenter;
import cn.edu.seu.alumni.mvp.presenter.auth.RegisterPresenter;
import cn.edu.seu.alumni.mvp.view.auth.IRegisterView;
import cn.edu.seu.alumni.util.DataProvider;
import cn.edu.seu.alumni.widget.MajorPickerView;

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

    @Bind(R.id.agree_with_protocol)
    CheckBox agreeWithProtocolCheckBox;

    private OptionsPickerView enrollYearPickerView;
    private MajorPickerView majorPickerView;


    private DataProvider.SeuMajors seuMajors;
    private ArrayList<String> enrollYears;

    private boolean enrollYearPickerViewIsShowing = false;
    private boolean majorPickerViewIsShowing = false;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected boolean hasToolBarBackButton() {
        return true;
    }

    @Override
    protected void initial() {
        setToolbarTitle(getString(R.string.register));
        iRegisterPresenter = new RegisterPresenter(this);

        /**
         * 初始化数据
         */
        seuMajors = DataProvider.getSeuMajorsData();
        enrollYears = DataProvider.getEnrollYearsData();

    }

    /**
     * 设置入学年份
     */
    @OnClick(R.id.enroll_year_linearlayout)
    public void enrollYearLayoutOnClick(){
        enrollYearPickerView = new OptionsPickerView(this);
        enrollYearPickerView.setPicker(enrollYears);
        enrollYearPickerView.setTitle("选择入学年份");
        enrollYearPickerView.setCyclic(false);
        enrollYearPickerView.setSelectOptions(enrollYears.size() - 1);
        enrollYearPickerView.setCancelable(true);
        enrollYearPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String enrollYear = enrollYears.get(option1);
                enrollYearTextView.setText(enrollYear);
                enrollYearPickerViewIsShowing = false;
            }
        });
        enrollYearPickerView.show();
        enrollYearPickerViewIsShowing = true;
    }


    /**
     * 设置学院、专业
     */
    @OnClick({R.id.major_linearlayout,R.id.department_linearlayout})
    public void setDepartmentAndMajor() {
        majorPickerView = new MajorPickerView(this);
        majorPickerView.setPicker(seuMajors.getDepartments(), seuMajors.getMajors(), true);
        majorPickerView.setTitle("选择院系和专业");
        majorPickerView.setCyclic(true, false, false);
        majorPickerView.setSelectOptions(0, 0);
        majorPickerView.setCancelable(true);

        majorPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String department = seuMajors.getDepartments().get(option1);
                String major = seuMajors.getMajors().get(option1).get(option2);
                departmentTextView.setText(department);
                majorTextView.setText(major);
                majorPickerViewIsShowing = false;
            }
        });
        majorPickerView.show();
        majorPickerViewIsShowing = true;
    }

    /**
     * 注册按钮
     */
    @OnClick(R.id.register_button)
    void registerButtonOnClick() {

        iRegisterPresenter.registerAlumni(new RegisterAlumniRequest(telephoneNumberEditText.getText().toString(),
                enrollYearTextView.getText().toString(), departmentTextView.getText().toString(),
                majorTextView.getText().toString(), passwordEditText.getText().toString()), agreeWithProtocolCheckBox.isChecked());
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

    @Override
    public void onBackPressed() {

        /**
         * 之所以用变量标识而不用enrollYearPickerView.isShowing()方法是因为发现其有bug
         */
        if(enrollYearPickerViewIsShowing){
            enrollYearPickerView.dismiss();
            enrollYearPickerViewIsShowing = false;
        }else if(majorPickerViewIsShowing){
            majorPickerView.dismiss();
            majorPickerViewIsShowing = false;
        }else{
            super.onBackPressed();
        }

    }
}