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

    public void jump(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jump(Class<?> clazz) {
        this.jump(clazz, null);
    }

    public void jumpThenFinish(Class<?> clazz, Bundle bundle) {
        this.jump(clazz, bundle);
        this.finish();
    }


    public void jumpThenFinish(Class<?> clazz) {
        this.jumpThenFinish(clazz, null);
    }

    public void jumpForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jumpForResult(Class<?> clazz, int requestCode) {
        this.jumpForResult(clazz, requestCode, null);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
