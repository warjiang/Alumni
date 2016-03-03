package cn.edu.seu.alumni.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avoscloud.leanchatlib.activity.AVChatActivity;
import com.avoscloud.leanchatlib.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.adapter.FriendAdapter;
import cn.edu.seu.alumni.model.Friend;
import cn.edu.seu.alumni.util.CharacterParser;
import cn.edu.seu.alumni.widget.SideBar;

/**
 *  人脉
 */
public class ContactsFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.contacts_listview)
    ListView listView;
    @Bind(R.id.letter)
    TextView letter;
    @Bind(R.id.sidebar)
    SideBar sidebar;

    private List<Friend> dataList = new ArrayList<>();
    private List<Friend> sourceDataList = new ArrayList<Friend>();
    private FriendAdapter adapter;

    private LayoutInflater infalter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_contacts;
    }


    @Override
    protected void initial() {

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                dataList.add(new Friend("阿" + i));
            } else {
                dataList.add(new Friend("北" + i));
            }
        }
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                dataList.add(new Friend("陈" + i));
            } else {
                dataList.add(new Friend("杜" + i));
            }
        }
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                dataList.add(new Friend(i + "张"));
            } else {
                dataList.add(new Friend(i + "杨"));
            }
        }

        if (dataList != null && dataList.size() > 0) {
            sourceDataList = filledData(dataList); //过滤数据为有字母的字段  现在有字母 别的数据没有
        }

        //还原除了带字母字段的其他数据
        for (int i = 0; i < dataList.size(); i++) {
            sourceDataList.get(i).setName(dataList.get(i).getName());
        }
        dataList = null; //释放资源

        // 根据a-z进行排序源数据
        Collections.sort(sourceDataList, new PinyinComparator());

        infalter = LayoutInflater.from(getActivity());
        View headView = infalter.inflate(R.layout.list_item_contact_header, null);
        RelativeLayout re_newfriends = (RelativeLayout) headView.findViewById(R.id.contacts_new_friends_relative_layout);
        re_newfriends.setOnClickListener(this);
        RelativeLayout re_alumniGood = (RelativeLayout) headView.findViewById(R.id.contacts_alumni_good_relative_layout);
        re_alumniGood.setOnClickListener(this);
        adapter = new FriendAdapter(getActivity(), sourceDataList);
        listView.addHeaderView(headView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AVChatActivity.class);
                intent.putExtra(Constants.MEMBER_ID, sourceDataList.get(position).getName());
                getContext().startActivity(intent);
            }
        });

        sidebar.setTextView(letter);
        //设置右侧触摸监听
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listView.setSelection(position);
                }

            }
        });
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
//    private void filterData(String filterStr) {
//        List<Friend> filterDateList = new ArrayList<Friend>();
//
//        if (TextUtils.isEmpty(filterStr)) {
//            filterDateList = sourceDataList;
//        } else {
//            filterDateList.clear();
//            for (Friend friendModel : sourceDataList) {
//                String name = friendModel.getName();
//                if (name.indexOf(filterStr.toString()) != -1 || CharacterParser.getSelling(name).startsWith(filterStr.toString())) {
//                    filterDateList.add(friendModel);
//                }
//            }
//        }
//
//        // 根据a-z进行排序
//        Collections.sort(filterDateList, new PinyinComparator());
//        adapter.updateListView(filterDateList);
//    }

    /**
     * 为ListView填充数据
     *
     */
    private List<Friend> filledData(List<Friend> lsit) {
        List<Friend> mFriendList = new ArrayList<Friend>();

        for (int i = 0; i < lsit.size(); i++) {
            Friend friendModel = new Friend();
            friendModel.setName(lsit.get(i).getName());
            //汉字转换成拼音
            String pinyin = CharacterParser.getSelling(lsit.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friendModel.setLetters(sortString.toUpperCase());
            } else {
                friendModel.setLetters("#");
            }

            mFriendList.add(friendModel);
        }
        return mFriendList;
    }

    private class PinyinComparator implements Comparator<Friend>{
        @Override
        public int compare(Friend o1, Friend o2) {
            if (o1.getLetters().equals("@")
                    || o2.getLetters().equals("#")) {
                return -1;
            } else if (o1.getLetters().equals("#")
                    || o2.getLetters().equals("@")) {
                return 1;
            } else {
                return o1.getLetters().compareTo(o2.getLetters());
            }
        }
    }

    public TextView getLetter() {
        return letter;
    }

    public void setLetter(TextView letter) {
        this.letter = letter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contacts_new_friends_relative_layout:
            //    readyGo(NewFriendsActivity.class);
                break;
            case R.id.contacts_alumni_good_relative_layout:
           //     readyGo(AlumniGoodActivity.class);
                break;
        }
    }
}
