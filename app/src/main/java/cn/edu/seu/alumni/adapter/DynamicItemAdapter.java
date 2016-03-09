package cn.edu.seu.alumni.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.UserInfoActivity;
import cn.edu.seu.alumni.javabean.DynamicItem;
import cn.edu.seu.alumni.widget.MyGridView;

public class DynamicItemAdapter extends BasisAdapter<DynamicItem, DynamicItemAdapter.viewHolder> {

    private Context context;
    private String[] images = {
            //"http://img3.imgtn.bdimg.com/it/u=2671181954,1302198727&fm=21&gp=0.jpg",
            //"http://img3.imgtn.bdimg.com/it/u=2284434357,2830498318&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2015527637,3623972403&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3527020364,2054046693&fm=23&gp=0.jpg",
    };

    //用于gridview的图片缓存
    private HashMap<String, DrawableRequestBuilder<String>> imageBuff = new HashMap<>();
    private ArrayDeque<String> imageKeysQueue = new ArrayDeque<>();
    private final int MAX_BUFF_SIZE = 5;

    public DynamicItemAdapter(Context mContext) {
        super(mContext, new ArrayList<DynamicItem>(), viewHolder.class);
        context = mContext;
    }

    public DynamicItemAdapter(Context mContext, List<DynamicItem> mEntities, Class<viewHolder> classType) {
        super(mContext, mEntities, classType);
        context = mContext;
    }

    @Override
    protected void setDataIntoView(viewHolder holder, DynamicItem entity) {

    }

    @Override
    protected void initViewHolder(View convertView, viewHolder holder) {
        holder.personInfoRl = (RelativeLayout) convertView.findViewById(R.id.circle_dynamic_item_person_info_relative_layout);

        holder.personInfoRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getmContext(), MyInformationActivity.class);
//                getmContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemLayout() {
        return R.layout.list_item_dynamic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(getItemLayout(), null);
            MyGridView gridView = (MyGridView) convertView.findViewById(R.id.circle_dynamic_list_card_view_grid_view);
            gridView.setNumColumns(images.length >= 3 ? 3 : images.length);
            gridView.setAdapter(new ImageAdapter(context));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

            RelativeLayout userInfoRelativeLayout= (RelativeLayout)convertView.findViewById(R.id.circle_dynamic_item_person_info_relative_layout);
            userInfoRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent(getContext(), UserInfoActivity.class));
                }
            });

        }
        return convertView;
    }

    private class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            final ImageView imageView;
            if (null == convertView) {
                imageView = new ImageView(context);
            } else {
                imageView = (ImageView) convertView;
            }
            DisplayMetrics dm = imageView.getResources().getDisplayMetrics();
            int screenWidthDip = dm.widthPixels;
            int width;
            int height;
            switch (images.length) {
                case 1:
                    width = height = screenWidthDip;
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case 2:
                    width = height = screenWidthDip / 2;
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
                default:
                    width = height = screenWidthDip / 3;
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            if (imageBuff.containsKey(images[pos])) {
                imageBuff.get(images[pos]).crossFade().override(width, height).into(imageView);
            } else {
                DrawableRequestBuilder<String> drb = Glide.with(context).load(images[pos]).placeholder(R.drawable.placeholder);
                drb.crossFade().override(width, height).into(imageView);
                if (imageKeysQueue.size() >= MAX_BUFF_SIZE) {
                    imageBuff.remove(imageKeysQueue.pop());
                }
                imageKeysQueue.push(images[pos]);
                imageBuff.put(images[pos], drb);
            }
            return imageView;
        }

    }

    public static class viewHolder {
        RelativeLayout personInfoRl;
    }
}