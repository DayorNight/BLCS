package blcs.lwb.utils.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blcs.lwb.utils.R;

public class LearnWebsiteAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public LearnWebsiteAdapter() {
        super(R.layout.adapter_lean_websiter);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adapter_lean_websiter,item);
    }

}
