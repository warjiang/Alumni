package cn.edu.seu.alumni.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.javabean.StatusItem;
import cn.edu.seu.alumni.widget.MyGridView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 鹏程 on 2016/3/13.
 */
public class StatusListItemAdapter extends BaseAdapter {

    private Context context;
    private List<StatusItem> statusItems;

    public StatusListItemAdapter(Context context, List<StatusItem> statusItems) {
        this.context = context;
        this.statusItems = statusItems;
    }

    @Override
    public int getCount() {
        return statusItems.size();
    }

    @Override
    public Object getItem(int position) {
        return statusItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StatusItemViewHolder holder = null;
        if(null!=convertView){
            holder = (StatusItemViewHolder)convertView.getTag();
        }else{
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_dynamic, null);

            holder = new StatusItemViewHolder();
            holder.photoView = (CircleImageView)convertView.findViewById(R.id.circle_dynamic_item_photo_cv);
            holder.nameTextView = (TextView)convertView.findViewById(R.id.circle_dynamic_item_photo_cv);
            holder.proTextView = (TextView)convertView.findViewById(R.id.circle_dynamic_item_pro_tv);
            holder.schoolTextView = (TextView)convertView.findViewById(R.id.circle_dynamic_item_college_tv);
            holder.postTimeTextView = (TextView)convertView.findViewById(R.id.circle_dynamic_item_time_tv);
            holder.contentTextView = (TextView)convertView.findViewById(R.id.circle_dynamic_item_content_tv);
            holder.gridView = (MyGridView)convertView.findViewById(R.id.circle_dynamic_list_card_view_grid_view);

            convertView.setTag(holder);
        }

        StatusItem item = (StatusItem)getItem(position);

        Glide.with(context).load(item.getUser_image()).placeholder(R.drawable.placeholder).crossFade().into(holder.photoView);
        holder.nameTextView.setText(item.getName());

        //TODO
        holder.proTextView.setText("");
        holder.schoolTextView.setText("");

        holder.contentTextView.setText(item.getNews_text());
        holder.postTimeTextView.setText(item.getPost_time());
        holder.gridView.setAdapter(new ImageAdapter(context, item.getImage()));

        return convertView;
    }

    private class StatusItemViewHolder{
        //头像
        private CircleImageView photoView;
        //name
        private TextView nameTextView;
        //专业
        private TextView proTextView;
        //学院
        private TextView schoolTextView;
        //状态发表时间
        private TextView postTimeTextView;
        //状态内容
        private TextView contentTextView;
        //图片九宫格
        private MyGridView gridView;
    }

    private class ImageAdapter extends BaseAdapter {

        private Context context;
        private String[] imageUrls;
        private HashMap<String, DrawableRequestBuilder<String>> imageBuff = new HashMap<>();

        public ImageAdapter(Context context, String[] imageUrls) {
            this.context = context;
            this.imageUrls = imageUrls;
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = new ImageView(context);
            }
            final ImageView imageView = (ImageView)convertView;
            DisplayMetrics dm = imageView.getResources().getDisplayMetrics();
            int screenWidthDip = dm.widthPixels;
            int width;
            int height;
            switch (imageUrls.length) {
                case 1:
                    width = height = (int)(screenWidthDip * 0.9f) *2 / 3 ;
                    imageView.setScaleType(ImageView.ScaleType.FIT_START);
                    break;
                default:
                    width = height = (int)(screenWidthDip * 0.9f) / 3 ;
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            if (imageBuff.containsKey(imageUrls[pos])) {
                imageBuff.get(imageUrls[pos]).crossFade().override(width, height).into(imageView);
            } else {
                DrawableRequestBuilder<String> drb = Glide.with(context).load(imageUrls[pos]).placeholder(R.drawable.placeholder);
                drb.crossFade().override(width, height).into(imageView);
                imageBuff.put(imageUrls[pos], drb);
            }
            return imageView;
        }

    }
}
