package cn.edu.seu.alumni.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import butterknife.Bind;
import cn.edu.seu.alumni.R;

public class StatusTextActivity extends SwipeBackBaseActivity {

    @Bind(R.id.comment_list_view)
    protected ListView commentListView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dynamic_text;
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
        setToolbarTitle("校友圈");

        commentListView.setAdapter(new CommentListViewAdapter(this));
        commentListView.setFocusable(false);
    }

    private static class CommentListViewAdapter extends BaseAdapter{

        private Context context;

        public CommentListViewAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(null==convertView){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_circle_dynamic_text_comment, null);
            }

            return convertView;
        }
    }
}
