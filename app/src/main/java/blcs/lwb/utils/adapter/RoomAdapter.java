package blcs.lwb.utils.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import blcs.lwb.utils.R;
import blcs.lwb.utils.bean.Student;

public class RoomAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {

    public RoomAdapter() {
        super(R.layout.adapter_sql_show);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.tv_adapter_sql_id, String.valueOf(item.getId()));
        helper.setText(R.id.tv_adapter_sql_name, item.getName());
        helper.setText(R.id.tv_adapter_sql_address, String.valueOf(item.getAge()));
    }
}
