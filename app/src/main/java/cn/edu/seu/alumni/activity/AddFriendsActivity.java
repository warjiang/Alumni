package cn.edu.seu.alumni.activity;

import android.view.MenuItem;
import android.view.View;

import butterknife.OnClick;
import cn.edu.seu.alumni.R;

public class AddFriendsActivity extends SwipeBackBaseActivity {


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
    protected boolean hasToolBarBackButton() {
        return true;
    }

    @Override
    protected void initial() {
        setToolbarTitle(this.getString(R.string.add_friend_));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
