package cn.edu.seu.alumni.service;

import android.content.Context;


import cn.edu.seu.alumni.util.PreferenceUtils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public final class ServiceProvider {

    private static final String BASE = "http://api2.hloli.me:4096/v1.0";
    private static Service service = null;
    static volatile RestAdapter restAdapter = null;

    private ServiceProvider() {
    }

    private static RestAdapter getRestAdapter(final Context mContext) {
        if (restAdapter == null) {
            synchronized (ServiceProvider.class) {
                if (restAdapter == null) {
                    RequestInterceptor requestInterceptor = new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            if(!PreferenceUtils.hasKey(mContext, PreferenceUtils.Key.LOGIN)
                                    || !PreferenceUtils.getBoolean(mContext,PreferenceUtils.Key.LOGIN)) {
                                //没有登录,游客身份访问
                                request.addHeader(PreferenceUtils.Key.ACCESS,"guest");
                            }else{
                                //登录过
                                request.addHeader(PreferenceUtils.Key.ACCESS, PreferenceUtils.getString(mContext, PreferenceUtils.Key.ACCESS));
                            }
                        }
                    };
                    restAdapter = new RestAdapter.Builder().setEndpoint(ServiceProvider.BASE).setLogLevel(RestAdapter.LogLevel.FULL).setRequestInterceptor(requestInterceptor).build();
                }
            }
        }
        return restAdapter;
    }

    public static Service getService(Context mContext) {
        if (service == null) {
            synchronized (ServiceProvider.class) {
                if (service == null) {
                    service = getRestAdapter(mContext).create(Service.class);
                }
            }
        }
        return service;
    }

}
