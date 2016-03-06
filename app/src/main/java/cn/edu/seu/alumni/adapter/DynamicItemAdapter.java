package cn.edu.seu.alumni.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.javabean.DynamicItem;
import cn.edu.seu.alumni.widget.MyGridView;

public class DynamicItemAdapter extends BasisAdapter<DynamicItem, DynamicItemAdapter.viewHolder> {

    private Context context;
    private int[] images = new int[5];

    public DynamicItemAdapter(Context mContext) {
        super(mContext, new ArrayList<DynamicItem>(), viewHolder.class);
        context = mContext;

        for (int i = 0; i < images.length; ++i) {
            images[i] = R.drawable.logo;
        }
    }

    public DynamicItemAdapter(Context mContext, List<DynamicItem> mEntities, Class<viewHolder> classType) {
        super(mContext, mEntities, classType);
        context = mContext;

        for (int i = 0; i < images.length; ++i) {
            images[i] = R.drawable.logo;
        }
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
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (null == convertView) {
                imageView = new ImageView(context);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(images[position]);
            return imageView;
        }

    }

    public static class viewHolder {
        RelativeLayout personInfoRl;
    }
}