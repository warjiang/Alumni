package cn.edu.seu.alumni.activity.my;

import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;

public class UpdateDetailedInfoActivity extends SwipeBackBaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_detailed_info;
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
        setToolbarTitle("基本资料");
    }

    @OnClick(R.id.update_user_img)
    public void updateUserImgOnClick(){

    }

    @OnClick(R.id.update_user_name)
    public void updateUserNameOnClick(){
        jump(UpdateUserNameActivity.class);
    }

    @OnClick(R.id.update_user_sex)
    public void updateUserSexOnClick(){

    }
    @OnClick(R.id.update_user_birthday)
    public void updateUserBirthdayOnClick(){

    }
    @OnClick(R.id.update_user_workcity)
    public void updateUserWorkCityOnClick(){

    }
    @OnClick(R.id.update_user_profession)
    public void updateUserProfessionOnClick(){

    }
    @OnClick(R.id.update_user_company)
    public void updateUserCompanyOnClick(){
        jump(UpdateUserCompanyActivity.class);
    }
    @OnClick(R.id.update_user_job)
    public void updateUserJobOnClick(){
        jump(UpdateUserJobActivity.class);
    }
    @OnClick(R.id.update_user_description)
    public void updateUserDescriptionOnClick(){
        jump(UpdateUserDecriptionActivity.class);
    }
    @OnClick(R.id.update_user_profession_experience)
    public void updateUserProfessionExperienceOnClick(){

    }
    @OnClick(R.id.update_user_education_experience)
    public void updateUserEducationExperienceOnClick(){

    }


}
