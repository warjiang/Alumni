package cn.edu.seu.alumni.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.adapter.BasisAdapter;
import cn.edu.seu.alumni.adapter.DynamicItemAdapter;
import cn.edu.seu.alumni.javabean.DynamicItem;
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

    //上拉加载更多
    private void onPullUp() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                circleListView.setLoading(false);
            }
        }, 2000);


    }

    private void onPullDown() {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

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
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_orange_light,  android.R.color.holo_blue_bright,
                android.R.color.holo_green_light);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullDown();
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View childView = scrollView.getChildAt(0);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (childView  != null && childView .getMeasuredHeight() <= scrollView.getScrollY() + scrollView.getHeight()) {

                            /**
                             * 此处添加加载更多的代码
                             */
                            Toast.makeText(CircleFragment.this.getActivity(), "onLoadMore", Toast.LENGTH_SHORT).show();
                            circleListView.setLoading(true);
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    circleListView.setLoading(false);
                                }
                            }, 2000);
                        }
                        else if (scrollView.getScrollY() == 0) {

                        }
                        break;
                }
                return false;
            }
        });

        List<DynamicItem> entities = new ArrayList<DynamicItem>();
        for (int i = 0; i < 5; i++) {
            entities.add(new DynamicItem());
        }
        adapter = new DynamicItemAdapter(getActivity());
        adapter.setEntities(entities);
        circleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        scrollView.smoothScrollTo(0, 0);
    }
}
