package blcs.lwb.utils.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;

public class HomeTabAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeTabAdapter() {
        super(R.layout.textview);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_home_name,(helper.getAdapterPosition()+1)+"→"+item);
    }

}
