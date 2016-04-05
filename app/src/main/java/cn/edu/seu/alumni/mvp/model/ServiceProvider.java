package cn.edu.seu.alumni.mvp.model;

import android.content.Context;


import cn.edu.seu.alumni.application.App;
import cn.edu.seu.alumni.util.Preference;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public final class ServiceProvider {

    private static final String BASE = "http://api.yoyocampus.com:4096/";
    private static IService service = null;
    static volatile RestAdapter restAdapter = null;

    private ServiceProvider() {
    }

    public static IService getService() {
        final Context context = App.getContext();
        if (service == null) {
            synchronized (ServiceProvider.class) {
                if (service == null) {
                    if(restAdapter == null){
                        restAdapter = new RestAdapter.Builder().setEndpoint(ServiceProvider.BASE).setLogLevel(RestAdapter.LogLevel.FULL).setRequestInterceptor(new RequestInterceptor() {
                            @Override
                            public void intercept(RequestFacade request) {
                                boolean isAccessTokenValid = Preference.getBoolean(Preference.Key.IS_ACCESS_TOKEN_VALID, false);
                                if(isAccessTokenValid){
                                    String accessToken = Preference.getString(Preference.Key.ACCESS_TOKEN, null);
                                    request.addHeader("access_token", accessToken);
                                }
                            }
                        }).build();
                    }
                    service = restAdapter.create(IService.class);
                }
            }
        }
        return service;
    }
}
