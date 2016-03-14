package cn.edu.seu.alumni.activity.circle;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;

public class PublishNewStatusActivity extends SwipeBackBaseActivity{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish_new_status;
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
        setToolbarTitle("发动态");
    }
}
