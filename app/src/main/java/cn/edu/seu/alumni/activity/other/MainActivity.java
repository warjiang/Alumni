package cn.edu.seu.alumni.activity.other;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.BaseActivity;
import cn.edu.seu.alumni.activity.circle.PublishNewStatusActivity;
import cn.edu.seu.alumni.activity.contacts.AddFriendsActivity;
import cn.edu.seu.alumni.fragment.CircleFragment;
import cn.edu.seu.alumni.fragment.ContactsFragment;
import cn.edu.seu.alumni.fragment.MyFragment;
import test.pc.PublishDynamicActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.viewpager)
    protected ViewPager viewPager;

    @Bind(R.id.circle_linearlayout)
    protected LinearLayout circleLinearLayout;

    @Bind(R.id.circle_imageview)
    protected ImageView circleImageView;

    @Bind(R.id.circle_textview)
    protected TextView circleTextView;

    @Bind(R.id.contacts_linearlayout)
    protected LinearLayout contactsLinearLayout;

    @Bind(R.id.contacts_imageview)
    protected ImageView contactsImageView;

    @Bind(R.id.contacts_textview)
    protected TextView contactsTextView;

    @Bind(R.id.my_linearlayout)
    protected LinearLayout myLinearLayout;

    @Bind(R.id.my_imageview)
    protected ImageView myImageView;

    @Bind(R.id.my_textview)
    protected TextView myTextView;

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentStatePagerAdapter adapter;
    private int currentIndex = 0;

    /**
     * 记录上一次按返回键的时间
     */
    private long exitTime = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected boolean hasToolBarBackButton() {
        return false;
    }

    @Override
    protected void initial() {

        fragments.add(new CircleFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new MyFragment());
        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                onSelected(position);
            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        circleLinearLayout.setOnClickListener(this);
        contactsLinearLayout.setOnClickListener(this);
        myLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circle_linearlayout:
                viewPager.setCurrentItem(0, false);
                onSelected(0);
                break;
            case R.id.contacts_linearlayout:
                viewPager.setCurrentItem(1, false);
                onSelected(1);
                break;
            case R.id.my_linearlayout:
                viewPager.setCurrentItem(2, false);
                onSelected(2);
                break;
        }
    }

    private void onSelected(int selectedItemId) {
        circleImageView.setImageResource(R.drawable.circle_default);
        circleTextView.setTextColor(getResources().getColor(R.color.main_text_normal));
        contactsImageView.setImageResource(R.drawable.contacts_default);
        contactsTextView.setTextColor(getResources().getColor(R.color.main_text_normal));
        myImageView.setImageResource(R.drawable.my_default);
        myTextView.setTextColor(getResources().getColor(R.color.main_text_normal));

        switch (selectedItemId) {
            case 0:
                circleImageView.setImageResource(R.drawable.circle_selected);
                circleTextView.setTextColor(getResources().getColor(R.color.main_text_selected));
                break;
            case 1:
                contactsImageView.setImageResource(R.drawable.contacts_selected);
                contactsTextView.setTextColor(getResources().getColor(R.color.main_text_selected));
                break;
            case 2:
                myImageView.setImageResource(R.drawable.my_selected);
                myTextView.setTextColor(getResources().getColor(R.color.main_text_selected));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem addFriendItem = menu.findItem(R.id.add_friend);
        MenuItem sendNewStatusItem = menu.findItem(R.id.send_new_status);
        switch (currentIndex) {
            case 0:
                sendNewStatusItem.setVisible(true);
                addFriendItem.setVisible(false);
                setToolbarTitle(getString(R.string.alumni_circle));
                break;
            case 1:
                sendNewStatusItem.setVisible(false);
                addFriendItem.setVisible(true);
                setToolbarTitle(getString(R.string.contacts));
                break;
            case 2:
                sendNewStatusItem.setVisible(false);
                addFriendItem.setVisible(false);
                setToolbarTitle(getString(R.string.alumni_circle));
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_new_status:
                jump(PublishNewStatusActivity.class);
                break;
            case R.id.add_friend:
                jump(AddFriendsActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            showToast("再按一次退出校友圈");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}
