package cn.edu.seu.alumni.activity;

import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.util.Preference;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserBasicInfoActivity extends SwipeBackBaseActivity {

    /**
     * 用户头像、姓名、入学年份学院、专业
     */
    @Bind(R.id.user_img)
    protected CircleImageView userImageView;

    @Bind(R.id.user_name)
    protected TextView userNameTextView;

    @Bind(R.id.user_enrollyear_and_department)
    protected TextView userEnrollyearAndDepartmentTextView;

    @Bind(R.id.user_major)
    protected TextView userMajorTextView;

    /**
     * 校友号
     */
    @Bind(R.id.alumni_number)
    protected TextView alumniNumberTextView;

    /**
     * 个人简介
     */
    @Bind(R.id.user_description)
    protected TextView userDescriptionTextView;

    /**
     *  最新动态
     */
    @Bind(R.id.user_newest_status)
    protected TextView userNewestStatus;

    /**
     *  职业经历
     */
    @Bind(R.id.professional_experiment_listview)
    protected ListView professionalExperimentListView;

    /**
     *  教育经历
     */
    @Bind(R.id.educational_experiment_listview)
    protected ListView educationalExperimentListView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_basic_info;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        boolean isAccessTokenValid = Preference.getBoolean(Preference.Key.IS_ACCESS_TOKEN_VALID, false);
        /**
         * 开发者
         */
        if(!isAccessTokenValid){

            String userName = "开发部" + (int) (11 * Math.random() + 1) + "番队队长";
            setToolbarTitle(userName);
            userNameTextView.setText(userName);
            userImageView.setImageResource(R.drawable.developer_img);

            String p[] = new String[]{"上天", "入地", "去青青草原抓羊", "卖萌"};
            professionalExperimentListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p));

            educationalExperimentListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p));

        }

        /**
         * 用户
         */
        else{
            userNameTextView.setText(Preference.getString(Preference.Key.NAME, "未设置姓名"));
            setToolbarTitle(Preference.getString(Preference.Key.NAME, "未设置姓名"));
            //TODO 加载userImageView头像

        }


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
