package cn.edu.seu.alumni.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.my.EditDetailedInfoActivity;
import cn.edu.seu.alumni.activity.my.UserBasicInfoActivity;
import cn.edu.seu.alumni.util.Preference;
import de.hdodenhof.circleimageview.CircleImageView;
import thirdpart.leancloud.ConversationListActivity;

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

//    @Bind(R.id.user_basic_info_relativelayout)
//    protected RelativeLayout userBasicInfoRelativeLayout;

    @OnClick(R.id.user_basic_info_relativelayout)
    protected void userBasicInfoRelativeLayoutOnClick(){
        jump(UserBasicInfoActivity.class);
    }

    /**
     * 编辑资料
     */
//    @Bind(R.id.update_user_detailed_info_relativelayout)
//    protected RelativeLayout updateUserDetailedInfoRelativeLayout;
    @OnClick(R.id.update_user_detailed_info_relativelayout)
    protected void updateUserDetailedInfoRelativeLayoutOnClick(){
        jump(EditDetailedInfoActivity.class);
    }

    /**
     * 我的二维码
     */
    @Bind(R.id.my_qrcode_relativelayout)
    protected RelativeLayout myQrcodeRelativeLayout;

    /**
     * 我的动态
     */
    @Bind(R.id.my_status_relativelayout)
    protected RelativeLayout myStatusRelativeLayout;

    /**
     * 我的信息
     */
    @Bind(R.id.my_message_relativelayout)
    protected RelativeLayout myMessageRelativeLayout;
    @OnClick(R.id.my_message_relativelayout)
    public void myMessageRelativeLayoutOnClick(View v){
        getActivity().startActivity(new Intent(getActivity(), ConversationListActivity.class));
    }

    /**
     * 我的收藏
     */
    @Bind(R.id.my_collection_relativelayout)
    protected RelativeLayout myCollectionRelativeLayout;

    /**
     * 设置
     */
    @Bind(R.id.settings_relativelayout)
    protected RelativeLayout settingsRelativeLayout;


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
