package blcs.lwb.utils.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.bean.StorageSpace;
import blcs.lwb.utils.R;


/**
 * Created by mrd on 2019/2/27.
 */

public class WeChatStorageAdapter extends BaseQuickAdapter<StorageSpace,BaseViewHolder> {
    private Activity activity;
    public WeChatStorageAdapter(Activity activity ) {
        super(R.layout.adapter_wechat_storage);
        this.activity = activity;
    }
    @Override
    protected void convert(BaseViewHolder helper, StorageSpace item) {
        helper.setText(R.id.tv_storage_titile,item.getTitle());
        helper.setText(R.id.tv_storage_size,item.getSize());
        helper.setText(R.id.tv_storage_content,item.getContent());
        Button storage = helper.getView(R.id.btn_storage_space);
        storage.setText(item.getButton());
        storage.setTag(helper.getAdapterPosition());
        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                Log.e(TAG, "onClick: "+tag);
                RxToast.info(activity,activity.getString(R.string.function_unopen));
            }
        });
    }
}