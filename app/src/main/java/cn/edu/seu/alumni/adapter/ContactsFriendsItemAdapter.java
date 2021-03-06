package cn.edu.seu.alumni.adapter;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.javabean.ContactsFriendsItem;

//import com.seu.wufan.alumnicircle.R;
//import com.seu.wufan.alumnicircle.model.item.ContactsFriendsItem;
//
//import java.util.ArrayList;
//import java.util.List;


public class ContactsFriendsItemAdapter extends BasisAdapter<ContactsFriendsItem, ContactsFriendsItemAdapter.viewHolder> {

    public ContactsFriendsItemAdapter(Context mContext) {
        super(mContext, new ArrayList<ContactsFriendsItem>(), viewHolder.class);
    }

    public ContactsFriendsItemAdapter(Context mContext, List<ContactsFriendsItem> mEntities, Class<viewHolder> classType) {
        super(mContext, mEntities, classType);
    }

    @Override
    protected void setDataIntoView(viewHolder holder, ContactsFriendsItem entity) {

    }

    @Override
    protected void initViewHolder(View convertView, viewHolder holder) {

    }

    @Override
    public int getItemLayout() {
        return R.layout.list_item_contacts_new_friends;
    }

    public static class viewHolder {

    }
}
