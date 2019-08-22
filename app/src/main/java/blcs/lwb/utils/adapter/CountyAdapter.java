package blcs.lwb.utils.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import blcs.lwb.lwbtool.bean.CountryBean;
import blcs.lwb.utils.R;

public class CountyAdapter extends BaseQuickAdapter<CountryBean, BaseViewHolder> {

    public CountyAdapter() {
        super(R.layout.adapter_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CountryBean item) {
        helper.setText(R.id.tv_list_name, item.getCn());
    }
}
