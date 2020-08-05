package blcs.lwb.utils.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import blcs.lwb.utils.R;

/**
 * @Author BLCS
 * @Time 2020/8/5 11:29
 */
public class UnuserdAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UnuserdAdapter() {
        super(R.layout.adapter_unuserd);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adapter_unuser,item);
    }
}
