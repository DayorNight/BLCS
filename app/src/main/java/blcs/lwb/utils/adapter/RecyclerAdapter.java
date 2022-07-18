package blcs.lwb.utils.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import blcs.lwb.utils.bean.HomeItem;
import blcs.lwb.utils.R;

public class RecyclerAdapter extends BaseQuickAdapter<HomeItem , BaseViewHolder> {

    public RecyclerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.item_tv_title, item.getTitle());
        helper.setImageResource(R.id.itme_iv_icon, item.getImageResource());
    }
}
