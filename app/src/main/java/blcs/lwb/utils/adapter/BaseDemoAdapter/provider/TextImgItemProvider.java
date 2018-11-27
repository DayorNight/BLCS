package blcs.lwb.utils.adapter.BaseDemoAdapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.DemoMultipleItemRvAdapter;
import blcs.lwb.utils.adapter.BaseDemoAdapter.NormalMultipleEntity;

/**
 * https://github.com/chaychan
 * @author ChayChan
 * @description: Text Img ItemProvider
 * @date 2018/3/30  11:39
 */
public class TextImgItemProvider extends BaseItemProvider<NormalMultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return DemoMultipleItemRvAdapter.TYPE_TEXT_IMG;
    }

    @Override
    public int layout() {
        return R.layout.item_img_text_view;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        helper.setText(R.id.item_multiple_tv, data.content);
        if (position % 2 == 0) {
            helper.setImageResource(R.id.item_multiple_img, R.mipmap.animation_img1);
        }else{
            helper.setImageResource(R.id.item_multiple_img, R.mipmap.animation_img2);
        }
    }

    @Override
    public void onClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
