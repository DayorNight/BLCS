package blcs.lwb.utils.adapter.BaseDemoAdapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.MultipleItemQuickAdapter;
import blcs.lwb.utils.adapter.BaseDemoAdapter.NormalMultipleEntity;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: Img ItemProvider
 * @date 2018/3/30  11:39
 */

public class ImgItemProvider extends BaseItemProvider<NormalMultipleEntity> {

    @Override
    public int getItemViewType() {
//        return MultipleItemQuickAdapter.TYPE_IMG;
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image_view;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
        int position = baseViewHolder.getAbsoluteAdapterPosition();
        if (position % 2 == 0) {
//            helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
        }else{
//            helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
        }
    }
}
