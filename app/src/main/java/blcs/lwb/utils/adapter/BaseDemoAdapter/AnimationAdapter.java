package blcs.lwb.utils.adapter.BaseDemoAdapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import blcs.lwb.utils.R;

public class AnimationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AnimationAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tweetName,item);
        addChildClickViewIds(R.id.adapter_item_img);
        addChildLongClickViewIds(R.id.adapter_item_img);
    }
}
