package cn.edu.seu.alumni.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.seu.alumni.R;

/**
 *  Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
        if(hasToolBar()){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbarTitleTextView = (TextView) findViewById(R.id.toolbar_title);
            setSupportActionBar(toolbar);
            /**
             * Toolbar左方不显示应用标题
             */
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            /**
             *   是否显示返回图标
             */
            getSupportActionBar().setDisplayHomeAsUpEnabled(hasToolBarBackButton());
        }

        ButterKnife.bind(this);
        initial();

    }

    /**
     * 设置布局文件
     */
    protected abstract int getContentViewId();

    /**
     * 是否有ToolBar
     */
    protected abstract boolean hasToolBar();

    /**
     * 是否有返回按钮
     */
    protected abstract boolean hasToolBarBackButton();

    /**
     * 设置toolbar标题
     */
    protected void setToolbarTitle(String title){
        if(hasToolBar()){
            toolbarTitleTextView.setText(title);
        }
    }

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
        finishWithAnimation();
    }

    public void finishWithAnimation(){
        this.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishWithAnimation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}