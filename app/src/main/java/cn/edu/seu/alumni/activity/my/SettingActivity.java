package cn.edu.seu.alumni.activity.my;

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
}
