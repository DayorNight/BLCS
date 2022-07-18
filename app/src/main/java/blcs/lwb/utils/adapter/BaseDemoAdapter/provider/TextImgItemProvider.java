package blcs.lwb.utils.adapter.BaseDemoAdapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.NormalMultipleEntity;

/**
 * https://github.com/chaychan
 * @author ChayChan
 * @description: Text Img ItemProvider
 * @date 2018/3/30  11:39
 */
public class TextImgItemProvider extends BaseItemProvider<NormalMultipleEntity> {


    @Override
    public int getItemViewType() {
//        return DemoMultipleItemRvAdapter.TYPE_TEXT_IMG;
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_img_text_view;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data) {
        helper.setText(R.id.item_multiple_tv, data.content);
        int position = helper.getLayoutPosition();
        if (position % 2 == 0) {
            helper.setImageResource(R.id.item_multiple_img, R.mipmap.animation_img1);
        }else{
            helper.setImageResource(R.id.item_multiple_img, R.mipmap.animation_img2);
        }
    }
}
