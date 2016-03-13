package cn.edu.seu.alumni.activity.contacts;

import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

//import com.seu.wufan.alumnicircle.R;
//import com.seu.wufan.alumnicircle.model.item.ContactsFriendsItem;
//import com.seu.wufan.alumnicircle.ui.activity.base.BaseSwipeActivity;
//import com.seu.wufan.alumnicircle.ui.adapter.base.BasisAdapter;
//import com.seu.wufan.alumnicircle.ui.adapter.contacts.ContactsAlumniGoodItemAdapter;
//import com.seu.wufan.alumnicircle.ui.view.ScrollLoadMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;
import cn.edu.seu.alumni.adapter.BasisAdapter;
import cn.edu.seu.alumni.adapter.ContactsAlumniGoodItemAdapter;
import cn.edu.seu.alumni.javabean.ContactsFriendsItem;


public class AlumniGoodActivity extends SwipeBackBaseActivity {

    @Bind(R.id.contacts_alumni_good_lm_list_view)
    ListView listView;
    //@Bind(R.id.contacts_alumni_good_scroll_view)
    //ScrollView mScrollView;
    private ContactsAlumniGoodItemAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contacts_alumni_good;
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
        setToolbarTitle(getString(R.string.alumni_good));

        List<ContactsFriendsItem> entities = new ArrayList<ContactsFriendsItem>();
        for (int i = 0; i < 10; i++) {
            entities.add(new ContactsFriendsItem());
        }

        adapter = new ContactsAlumniGoodItemAdapter(this);
        adapter.setEntities(entities);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
