package cn.edu.seu.alumni.activity.my;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;

public class UpdateUserDecriptionActivity extends SwipeBackBaseActivity{
    @Override
    protected int getContentViewId() {
        return R.layout.activity_update_user_description;
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
        setToolbarTitle("个人简介");
    }
}
