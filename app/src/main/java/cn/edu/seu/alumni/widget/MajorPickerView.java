package cn.edu.seu.alumni.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.view.WheelOptions;

import cn.edu.seu.alumni.R;

public class MajorPickerView<T> extends OptionsPickerView<T> {

    public MajorPickerView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.major_pickerview;
    }
}
