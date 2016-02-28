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

/**
 * 圈子
 */
public class CircleFragment extends BaseFragment {

    /*
    @Bind(R.id.circle_top_topic_card_view)
    CardView mTopicCardView;
    @Bind(R.id.circle_dynamic_load_more_list_view)
    ScrollLoadMoreListView mDynamicLoadMoreLv;
    @Bind(R.id.circle_scroll_view)
    ScrollView mScrollView;
    @Bind(R.id.circle_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BasisAdapter mAdapter;

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {
//        mScrollView.smoothScrollTo(0, 0);
    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullDown();
            }
        });
        mDynamicLoadMoreLv.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onPullUp();
            }
        });
        mDynamicLoadMoreLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TLog.i("INFO", "position" + position);
                readyGo(DynamicTextActivity.class);
            }
        });
        mDynamicLoadMoreLv.setFocusable(false);
        initDatas();
        mScrollView.smoothScrollTo(0, 0);


    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_circle;
    }

    private void initDatas() {
        List<DynamicItem> entities = new ArrayList<DynamicItem>();
        for (int i = 0; i < 20; i++) {
            entities.add(new DynamicItem());
        }
        mAdapter = new DynamicItemAdapter(getActivity());
        mAdapter.setmEntities(entities);
        mDynamicLoadMoreLv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void onPullUp() { //上拉刷新，加载更多
        mDynamicLoadMoreLv.setLoading(false);
    }

    private void onPullDown() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.circle_top_topic_card_view)
    void topTopicView() {
        readyGo(CircleTopicActivity.class);
    }
    */

    @Override
    protected int getContentViewId() {
        return  R.layout.fragment_circle;
    }


    @Override
    protected void initial() {

    }
}
