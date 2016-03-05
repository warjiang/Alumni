package cn.edu.seu.alumni.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import cn.edu.seu.alumni.R;


public class LoadMoreListView extends ListView implements OnScrollListener {

    private static final String TAG = LoadMoreListView.class
            .getSimpleName();

    private OnScrollListener onScrollListener;
    private LayoutInflater inflater;

    private View footerView;
    private View loadMoreStatusView;

    private OnLoadMoreListener onLoadMoreListener;

    private boolean isLoadingMore = false;
    private int currentScrollState;

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {

        super.setAdapter(adapter);
    }

    private void init(Context context) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        footerView = (RelativeLayout) inflater.inflate(R.layout.load_more_footer, this, false);
        loadMoreStatusView = footerView.findViewById(R.id.load_more_progress_bar);
        addFooterView(footerView);
        setLoading(false);
        super.setOnScrollListener(this);
    }

    public void setLoadMoreStatusView(View v, int statusViewId) {
        removeFooterView(footerView);

        footerView = v;
        loadMoreStatusView = footerView.findViewById(statusViewId);
        addFooterView(footerView);
    }

    public void setLoadMoreStatusView(View v) {
        removeFooterView(footerView);

        footerView = v;
        loadMoreStatusView = footerView.findViewById(R.id.load_more_progress_bar);
        addFooterView(footerView);
    }

    /**
     * Set the listener that will receive notifications every time the list
     * scrolls.
     *
     * @param l The scroll listener.
     */
    @Override
    public void setOnScrollListener(OnScrollListener l) {
        onScrollListener = l;
    }

    /**
     * Register a callback to be invoked when this list reaches the end (last item
     * be visible)
     *
     * @param onLoadMoreListener The callback to run.
     */

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        onLoadMoreListener = onLoadMoreListener;
    }

    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (onScrollListener != null) {
            onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                    totalItemCount);
        }

        if (visibleItemCount == totalItemCount) {
            if (loadMoreStatusView != null) {
                loadMoreStatusView.setVisibility(View.GONE);
            }
            return;
        }

        if (onLoadMoreListener != null) {

            boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;

            if (!isLoadingMore && loadMore
                    && currentScrollState != SCROLL_STATE_IDLE) {
                setLoading(true);
                onLoadMore();
            }
        }
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        currentScrollState = scrollState;

        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    public void setLoading(boolean loading) {
        Log.d(TAG, "setLoading: " + loading);

        isLoadingMore = loading;
        loadMoreStatusView.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void onLoadMore() {
        Log.d(TAG, "onLoadMore");
        if (onLoadMoreListener != null) {
            onLoadMoreListener.onLoadMore();
        }
    }

    public void onLoadMoreComplete() {
        setLoading(false);
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }
}