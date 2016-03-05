package cn.edu.seu.alumni.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.ButterKnife;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.util.CommonUtils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import retrofit.RetrofitError;

public abstract class SwipeBackBaseActivity extends SwipeBackActivity{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initial();

    }

    /**
     * 设置布局文件
     */
    protected abstract int getContentViewId();

    /**
     * 初始化
     */
    protected abstract void initial();

    public void jumpTo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jumpTo(Class<?> clazz) {
        this.jumpTo(clazz, null);
    }

    public void jumpToThenFinish(Class<?> clazz, Bundle bundle) {
        this.jumpTo(clazz, bundle);
        this.finish();
    }


    public void jumpToThenFinish(Class<?> clazz) {
        this.jumpToThenFinish(clazz, null);
    }

    public void jumpToForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jumpToForResult(Class<?> clazz, int requestCode) {
        this.jumpToForResult(clazz, requestCode, null);
    }

    protected void showMsg(String msg) {
        if (null != msg && !CommonUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showNetWorkError() {
        showMsg(getResources().getString(R.string.network_error_tips));
    }

    protected void showInnerError(RetrofitError error) {
        if (error != null)
            if(error.getBody() == null){
                showMsg(getString(R.string.request_time_out));
            }else{
                showMsg(CommonUtils.getErrorInfo(error).getReason());
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}