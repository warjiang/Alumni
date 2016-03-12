package cn.edu.seu.alumni.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.my.EditDetailedInfoActivity;
import cn.edu.seu.alumni.activity.my.MyCollectionActivity;
import cn.edu.seu.alumni.activity.my.MyMessageActivity;
import cn.edu.seu.alumni.activity.my.MyQrCodeActivity;
import cn.edu.seu.alumni.activity.my.MyStatusActivity;
import cn.edu.seu.alumni.activity.my.SettingActivity;
import cn.edu.seu.alumni.activity.my.UserBasicInfoActivity;
import cn.edu.seu.alumni.util.Preference;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我
 */
public class MyFragment extends BaseFragment {

    /**
     * 个人基本信息
     */
    @Bind(R.id.user_img)
    protected CircleImageView userImageView;

    @Bind(R.id.user_name)
    protected TextView userNameTextView;

    @OnClick(R.id.user_basic_info_relativelayout)
    protected void userBasicInfoRelativeLayoutOnClick(){
        jump(UserBasicInfoActivity.class);
    }

    /**
     * 编辑资料
     */
    @OnClick(R.id.update_user_detailed_info_relativelayout)
    protected void updateUserDetailedInfoRelativeLayoutOnClick(){
        jump(EditDetailedInfoActivity.class);
    }

    /**
     * 我的二维码
     */
    @OnClick(R.id.my_qrcode_relativelayout)
    protected void myQrcodeRelativeLayoutOnClick(){
        jump(MyQrCodeActivity.class);
    }

    /**
     * 我的动态
     */
    @OnClick(R.id.my_status_relativelayout)
    protected void myStatusRelativeLayoutOnClick(){
        jump(MyStatusActivity.class);
    }

    /**
     * 我的信息
     */
    @OnClick(R.id.my_message_relativelayout)
    public void myMessageRelativeLayoutOnClick(View v){
        jump(MyMessageActivity.class);
    }

    /**
     * 我的收藏
     */
    @OnClick(R.id.my_collection_relativelayout)
    public void myCollectionRelativeLayoutOnClick(View v){
        jump(MyCollectionActivity.class);
    }


    /**
     * 设置
     */
    @OnClick(R.id.settings_relativelayout)
    public void settingsRelativeLayoutOnClick(View v){
        jump(SettingActivity.class);
    }


    @Override
    protected void initial() {

        boolean isAccessTokenValid = Preference.getBoolean(Preference.Key.IS_ACCESS_TOKEN_VALID, false);
        /**
         * 开发者
         */
        if(!isAccessTokenValid){
            userNameTextView.setText("开发" + (int) (11 * Math.random() + 1) +"番队队长");
            userImageView.setImageResource(R.drawable.developer_img);

        }
        /**
         * 用户
         */
        else{
            userNameTextView.setText(Preference.getString(Preference.Key.NAME, "未设置姓名"));
            //TODO 加载userImageView头像

        }


    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_me;
    }

}
