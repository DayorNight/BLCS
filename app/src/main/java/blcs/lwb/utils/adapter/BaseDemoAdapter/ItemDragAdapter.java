package blcs.lwb.utils.adapter.BaseDemoAdapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import blcs.lwb.utils.R;

public class ItemDragAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {
    public ItemDragAdapter(List<String> data) {
        super(R.layout.item_img_text_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_multiple_tv,item);
    }
}
