package cn.edu.seu.alumni.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
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
            images[i] = R.drawable.test_image;
        }
    }

    public DynamicItemAdapter(Context mContext, List<DynamicItem> mEntities, Class<viewHolder> classType) {
        super(mContext, mEntities, classType);
        context = mContext;

        for (int i = 0; i < images.length; ++i) {
            images[i] = R.drawable.test_image;
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
            DisplayMetrics dm = imageView.getResources().getDisplayMetrics();
            int screenWidthDip = dm.widthPixels;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(imageView.getResources(), images[position], options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;

            if (1 == images.length || 2 == images.length) {
                int x = imageHeight * screenWidthDip / imageWidth;
                x = x < screenWidthDip ? x : screenWidthDip;
                x /= images.length;
                imageView.setImageBitmap(decodeSampledBitmapFromResource(imageView.getResources(), images[position], x, x));
            } else {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageBitmap(decodeSampledBitmapFromResource(imageView.getResources(),
                        images[position], screenWidthDip / 3, screenWidthDip / 3));
            }

            return imageView;
        }

        private int calculateInSampleSize(
                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // 原始图片的宽高
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                while ((halfHeight / inSampleSize) > reqHeight
                        || (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2;
                }
            }

            return inSampleSize;
        }

        private Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                       int reqWidth, int reqHeight) {

            // 首先设置 inJustDecodeBounds=true 来获取图片尺寸
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, options);

            // 计算 inSampleSize 的值
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // 根据计算出的 inSampleSize 来解码图片生成Bitmap
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, resId, options);
        }
    }

    public static class viewHolder {
        RelativeLayout personInfoRl;
    }
}