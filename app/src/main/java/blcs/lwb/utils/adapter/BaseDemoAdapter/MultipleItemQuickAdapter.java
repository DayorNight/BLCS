package blcs.lwb.utils.adapter.BaseDemoAdapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import blcs.lwb.utils.R;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.IMG, R.layout.item_image_view);
        addItemType(MultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.item_multiple_tv,item.getContent());
                break;
            case MultipleItem.IMG:
//                helper.setImageResource(R.id.item_multiple_img,);
                break;
            case MultipleItem.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.item_multiple_img, R.mipmap.animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.item_multiple_img, R.mipmap.animation_img2);
                        break;
                }
                break;
        }
    }
}
