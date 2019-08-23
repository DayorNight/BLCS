package blcs.lwb.utils.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blcs.lwb.lwbtool.bean.CityBean;
import blcs.lwb.utils.R;

public class CountyAdapter extends BaseQuickAdapter<CityBean, BaseViewHolder> {

    public CountyAdapter() {
        super(R.layout.adapter_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityBean item) {
        helper.setText(R.id.tv_list_name, item.getN());
        helper.setImageResource(R.id.iv_adapter,R.mipmap.ic_address );
    }
}
