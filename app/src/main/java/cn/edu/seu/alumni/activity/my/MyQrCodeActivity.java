package cn.edu.seu.alumni.activity.my;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;

public class MyQrCodeActivity extends SwipeBackBaseActivity{
    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_qrcode;
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
        setToolbarTitle("我的二维码");
    }
}
