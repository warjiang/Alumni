package cn.edu.seu.alumni.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import thirdpart.leancloud.ConversationListActivity;

/**
 * 我
 */
public class MyFragment extends BaseFragment {

    @Bind(R.id.me_my_message_linear_layout)
    protected LinearLayout myMessageLinearLayout;

    @OnClick(R.id.me_my_message_linear_layout)
    public void myMessageLinearLayoutOnClick(View v){
        getActivity().startActivity(new Intent(getActivity(), ConversationListActivity.class));
    }

    @Override
    protected void initial() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_me;
    }

}
