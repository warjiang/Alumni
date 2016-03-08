package cn.edu.seu.alumni.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.adapter.CircleTopicRecyclerViewAdapter;
import cn.edu.seu.alumni.util.CustomLinearLayoutManager;

public class CircleTopicActivity extends SwipeBackBaseActivity {

    @Bind(R.id.circle_topic_text_view)
    protected TextView circleTopicTextView;
    @Bind(R.id.cirlce_topic_nested_scroll_view)
    protected NestedScrollView nestedScrollView;
    @Bind(R.id.circle_topic_content_text_view)
    protected TextView topicContentTextView;
    @Bind(R.id.circle_join_topic_button)
    protected Button joinTopicButton;
    @Bind(R.id.circle_topic_recycle_view)
    protected RecyclerView topicRecyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_circle_topic;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected void initial() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        topicRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        topicRecyclerView.setAdapter(new CircleTopicRecyclerViewAdapter());
        topicRecyclerView.setNestedScrollingEnabled(false);
        topicRecyclerView.setHasFixedSize(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
