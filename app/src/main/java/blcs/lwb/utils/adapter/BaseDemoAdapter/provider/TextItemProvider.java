package blcs.lwb.utils.adapter.BaseDemoAdapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import blcs.lwb.utils.R;
//import blcs.lwb.utils.adapter.BaseDemoAdapter.DemoMultipleItemRvAdapter;
import blcs.lwb.utils.adapter.BaseDemoAdapter.NormalMultipleEntity;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: Text ItemProvider
 * @date 2018/3/30  11:39
 */

public class TextItemProvider extends BaseItemProvider<NormalMultipleEntity> {

    @Override
    public int getItemViewType() {
        return 3;
//        return DemoMultipleItemRvAdapter.TYPE_TEXT;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text_view;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data) {
        helper.setText(R.id.item_multiple_tv, data.content);
    }
}
