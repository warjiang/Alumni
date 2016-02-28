package cn.edu.seu.alumni.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.adapter.BasisAdapter;
import cn.edu.seu.alumni.adapter.DynamicItemAdapter;
import cn.edu.seu.alumni.model.DynamicItem;
import cn.edu.seu.alumni.widget.LoadMoreListView;
import cn.edu.seu.alumni.widget.ScrollLoadMoreListView;

/**
 * 圈子
 */
public class CircleFragment extends BaseFragment {


    @Bind(R.id.circle_topic_cardview)
    CardView topicCardView;

    @Bind(R.id.circle_listview)
    ScrollLoadMoreListView circleListView;

    @Bind(R.id.circle_scrollview)
    ScrollView scrollView;

    @Bind(R.id.circle_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private BasisAdapter adapter;




    private void onPullUp() { //上拉刷新，加载更多
        circleListView.setLoading(false);
    }

    private void onPullDown() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.circle_topic_cardview)
    void topTopicView() {
//        readyGo(CircleTopicActivity.class);
    }


    @Override
    protected int getContentViewId() {
        return  R.layout.fragment_circle;
    }


    @Override
    protected void initial() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullDown();
            }
        });
        circleListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onPullUp();
            }
        });
        circleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TLog.i("INFO", "position" + position);
//                readyGo(DynamicTextActivity.class);
            }
        });
        circleListView.setFocusable(false);

        List<DynamicItem> entities = new ArrayList<DynamicItem>();
        for (int i = 0; i < 20; i++) {
            entities.add(new DynamicItem());
        }
        adapter = new DynamicItemAdapter(getActivity());
        adapter.setmEntities(entities);
        circleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        scrollView.smoothScrollTo(0, 0);
    }
}
