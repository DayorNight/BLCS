package blcs.lwb.utils.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blcs.lwb.lwbtool.bean.VersionBean;
import blcs.lwb.utils.R;

public class FunctionIntroAdapter extends BaseQuickAdapter<VersionBean, BaseViewHolder> {
    public FunctionIntroAdapter() {
        super(R.layout.adapter_function_intro);
    }

    @Override
    protected void convert(BaseViewHolder helper, VersionBean item) {

        helper.setText(R.id.tv_adapter_function_name,"BLCS"+item.getVersionName()+"主要更新");
        helper.setText(R.id.tv_adapter_function_time,item.getPublicTime());
    }
}
