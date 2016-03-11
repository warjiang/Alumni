package cn.edu.seu.alumni.activity;

import android.view.MenuItem;

import cn.edu.seu.alumni.R;

public class UserBasicInfoActivity extends SwipeBackBaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_basic_info;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected void initial() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolbarTitle("校友圈");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
