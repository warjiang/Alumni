package cn.edu.seu.alumni.activity.my;

import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;

public class SettingActivity extends SwipeBackBaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
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
        setToolbarTitle("设置");
    }

    @OnClick(R.id.about_us)
    protected void aboutUsOnClick(){
        jump(AboutUsActivity.class);
    }

    @OnClick(R.id.logout)
    protected void logoutOnClick(){

    }


}
