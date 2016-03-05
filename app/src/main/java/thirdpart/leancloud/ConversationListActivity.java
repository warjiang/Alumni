package thirdpart.leancloud;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.callback.AVIMSingleMessageQueryCallback;
import com.avoscloud.leanchatlib.activity.AVChatActivity;
import com.avoscloud.leanchatlib.adapter.CommonListAdapter;
import com.avoscloud.leanchatlib.event.ConversationItemClickEvent;
import com.avoscloud.leanchatlib.model.Room;
import com.avoscloud.leanchatlib.utils.Constants;
import com.avoscloud.leanchatlib.utils.ConversationManager;
import com.avoscloud.leanchatlib.view.DividerItemDecoration;
import com.avoscloud.leanchatlib.viewholder.ConversationItemHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.edu.seu.alumni.R;
import de.greenrobot.event.EventBus;

public class ConversationListActivity extends AppCompatActivity {

    View imClientStateView;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private CommonListAdapter<Room> itemAdapter;
    private LinearLayoutManager layoutManager;
    private ConversationManager conversationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        imClientStateView = (View)findViewById(R.id.im_client_state_view);
        conversationManager = ConversationManager.getInstance();
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.fragment_conversation_srl_pullrefresh);
        refreshLayout.setEnabled(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView)findViewById(R.id.fragment_conversation_srl_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        itemAdapter = new CommonListAdapter<Room>(ConversationItemHolder.class);
        recyclerView.setAdapter(itemAdapter);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateConversationList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void updateConversationList() {
        conversationManager.findAndCacheRooms(new Room.MultiRoomsCallback() {
            @Override
            public void done(List<Room> roomList, AVException exception) {
                if (null == exception) {
                    List<Room> sortedRooms = sortRooms(roomList);
                    updateLastMessage(sortedRooms);
                    itemAdapter.setDataList(sortedRooms);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void updateLastMessage(final List<Room> roomList) {
        for (final Room room : roomList) {
            AVIMConversation conversation = room.getConversation();
            if (null != conversation) {
                conversation.getLastMessage(new AVIMSingleMessageQueryCallback() {
                    @Override
                    public void done(AVIMMessage avimMessage, AVIMException e) {
                        if (null != e && null != avimMessage) {
                            room.setLastMessage(avimMessage);
                            int index = roomList.indexOf(room);
                            itemAdapter.notifyItemChanged(index);
                        }
                    }
                });
            }
        }
    }

    private List<Room> sortRooms(final List<Room> roomList) {
        List<Room> sortedList = new ArrayList<Room>();
        if (null != roomList) {
            sortedList.addAll(roomList);
            Collections.sort(sortedList, new Comparator<Room>() {
                @Override
                public int compare(Room lhs, Room rhs) {
                    long value = lhs.getLastModifyTime() - rhs.getLastModifyTime();
                    if (value > 0) {
                        return -1;
                    } else if (value < 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }
        return sortedList;
    }

    public void onEvent(ConversationItemClickEvent clickEvent) {
        Intent intent = new Intent(this, AVChatActivity.class);
        intent.putExtra(Constants.CONVERSATION_ID, clickEvent.conversationId);
        startActivity(intent);
    }

    public void onEvent(ConversationFragmentUpdateEvent updateEvent) {
        updateConversationList();
    }

}
