package cn.edu.seu.alumni.activity;

import android.view.View;

import butterknife.OnClick;
import cn.edu.seu.alumni.R;

//import com.seu.wufan.alumnicircle.R;
//import com.seu.wufan.alumnicircle.ui.activity.base.BaseSwipeActivity;


public class AddFriendsActivity extends SwipeBackBaseActivity {

    /*
    @Bind(R.id.text_toolbar_tv)
    TextView mToolbarTv;


    @Override
    protected int getContentView() {
        return R.layout.activity_contacts_add_friends;
    }




    private void initToolBars() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //mToolbarTv.setVisibility(View.VISIBLE);
        //mToolbarTv.setText(R.string.add_friend_);
    }
    */

    @OnClick({R.id.contacts_add_friends_phone_ll, R.id.contacts_add_friends_alumni_ll, R.id.contacts_add_friends_scan_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contacts_add_friends_phone_ll:
                break;
            case R.id.contacts_add_friends_alumni_ll:
                break;
            case R.id.contacts_add_friends_scan_ll:
                break;
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contacts_add_friends;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected void initial() {
        //setTitle(R.string.add_friend_);
        //super.setTitle();
        setToolbarTitle(this.getString(R.string.add_friend_));
    }
}
