package blcs.lwb.utils.adapter.BaseDemoAdapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import blcs.lwb.utils.R;

public class HeaderAndFooterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HeaderAndFooterAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item_multiple_tv,item);
    }
}
