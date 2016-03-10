package cn.edu.seu.alumni.activity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.adapter.ContactsFriendsItemAdapter;
import cn.edu.seu.alumni.javabean.ContactsFriendsItem;

//import com.seu.wufan.alumnicircle.R;
//import com.seu.wufan.alumnicircle.model.item.ContactsFriendsItem;
//import com.seu.wufan.alumnicircle.ui.activity.base.BaseSwipeActivity;
//import com.seu.wufan.alumnicircle.ui.adapter.base.BasisAdapter;
//import com.seu.wufan.alumnicircle.ui.adapter.contacts.ContactsFriendsItemAdapter;
//import com.seu.wufan.alumnicircle.ui.view.ScrollLoadMoreListView;


public class NewFriendsActivity extends SwipeBackBaseActivity {

    private ContactsFriendsItemAdapter adapter;


    //@Bind(R.id.contacts_new_friends_lm_list_view)
    //ScrollLoadMoreListView mListView;
    @Bind(R.id.contacts_new_friends_lm_list_view)
    ListView listView;

    /*@Bind(R.id.contacts_new_friends_scroll_view)
    ScrollView mScrollView;*/

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contacts_new_friends;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected void initial() {
        //设置Toolbar标题
        setToolbarTitle(getString(R.string.new_friend));
        //mScrollView.smoothScrollTo(0, 0);

        List<ContactsFriendsItem> entities = new ArrayList<ContactsFriendsItem>();
        for (int i = 0; i < 10; i++) {
            entities.add(new ContactsFriendsItem());
        }

        /*ArrayAdapter<ContactsFriendsItem> arrayAdapter = new ArrayAdapter<ContactsFriendsItem>(
                this,
                R.layout.view_in_contacts_friend_item,
                entities
        );

        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();*/
        adapter = new ContactsFriendsItemAdapter(this);
        adapter.setEntities(entities);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*
    private BasisAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_contacts_new_friends;
    }

    @Override
    protected void prepareDatas() {

    }
    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        mScrollView.smoothScrollTo(0,0);
        List<ContactsFriendsItem> entities = new ArrayList<ContactsFriendsItem>();
        for (int i = 0; i < 10; i++) {
            entities.add(new ContactsFriendsItem());
        }
        mAdapter = new ContactsFriendsItemAdapter(this);
        mAdapter.setmEntities(entities);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    */


}
