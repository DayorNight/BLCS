package blcs.lwb.lwbtool.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blcs.lwb.lwbtool.R;


public class DialogFragmentAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public DialogFragmentAdapter() {
        super(R.layout.adapter_dialog_fragment_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adapter_dialog_fragment,item);
    }
}
