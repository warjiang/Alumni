package cn.edu.seu.alumni.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.seu.alumni.javabean.Error;
import retrofit.RetrofitError;

public class CommonUtil {

    /**
     * 字符串工具
     */
    public static class String {
        public static boolean isEmpty(java.lang.String s) {
            return s == null || s.equals("");
        }
    }

    /**
     * 正则表达式
     */
    public static class Regex {
        public static boolean match(java.lang.String regex, java.lang.String text) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            return matcher.find();
        }
    }

    /**
     * Retrofit错误处理
     */
    public static class Retrofit {
        public static java.lang.String getErrorReason(RetrofitError retrofitError) {
            if (retrofitError == null || retrofitError.getBody() == null) {
                return "未知错误";
            }
            cn.edu.seu.alumni.javabean.Error error = (Error) retrofitError.getBodyAs(Error.class);
            if (error == null || String.isEmpty(error.getReason()))
                return "未知错误";
            return error.getReason();
        }
    }

    /**
     * 屏幕密度
     */
    public static class Density {
        public static int dip2px(Context context, float dip) {
            float density = context.getResources().getDisplayMetrics().density;
            return (int) (dip * density + 0.5f);
        }

        public static int px2dip(Context context, float px) {
            float density = context.getResources().getDisplayMetrics().density;
            return (int) (px / density + 0.5f);
        }
    }

    /**
     * 网络连接
     */
    public static class Network {

        public static final int NOT_CONNECTED = 0;

        //WIFI开启
        public static final int WIFI = 1;

        //数据流量开启
        public static final int DATA = 2;

        public static int getNetworkState(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo data = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean wifiOn = (wifi != null && wifi.isConnected());
            boolean dataOn = (data != null && data.isConnected());
            if (!wifiOn && !dataOn) {
                return NOT_CONNECTED;
            } else if (wifiOn) {
                return WIFI;
            } else {
                return DATA;
            }
        }

        public static boolean isConnected(Context context) {
            int state = getNetworkState(context);
            return state == DATA || state == WIFI;
        }
    }

    public static class Image {

        public static byte[] bitmapToByteArray(Bitmap bitmap) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }

        public static Bitmap byteArrayToBitmap(byte[] data){

            return BitmapFactory.decodeByteArray(data, 0, data.length);
        }
    }

















        /*
    //使用Glide加载网络图片
    public static void showImageWithGlide(Context context, final CircleImageView imageView,String url){
        Glide.with(context)
                .load(url)
                .placeholder(new ColorDrawable(context.getResources().getColor(R.color.no_image_color)))
                .centerCrop()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    //使用Glide加载网络图片
    public static void showCircleImageWithGlide(Context context, final CircleImageView imageView,String url){
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.logo)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }
    //使用Glide加载网络图片
    public static void showImageWithGlide(Context context, final ImageView imageView,String url){
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.logo)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }
*/
}
