package cn.edu.seu.alumni.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.seu.alumni.R;

public class ResultActivity extends AppCompatActivity {

    @Bind(R.id.result_content)
    TextView mResultContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        setTitle("扫描结果");
        Bundle extras = getIntent().getExtras();
        mResultContent.setText(extras.getString("result"));
    }
}
