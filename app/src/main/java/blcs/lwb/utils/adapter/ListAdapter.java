package blcs.lwb.utils.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import blcs.lwb.utils.R;

public class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ListAdapter() {
        super(R.layout.adapter_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_list_name, item);
    }
}
