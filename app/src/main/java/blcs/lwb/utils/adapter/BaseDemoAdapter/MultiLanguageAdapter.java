package blcs.lwb.utils.adapter.BaseDemoAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import blcs.lwb.lwbtool.utils.SPUtils;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;

public class MultiLanguageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Activity activity;
    public MultiLanguageAdapter(Activity activity) {
        super(R.layout.adapter_multi_language);
        this.activity = activity;
    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int pos = (int) SPUtils.get(activity, Constants.SP_MultiLanguage, 0);
        helper.setText(R.id.tv_multi_language,item);
        RadioButton rb = helper.getView(R.id.rb_multi_language);
        if(pos==helper.getAdapterPosition()){
            rb.setButtonTintList((ColorStateList)activity.getResources().getColorStateList(R.color.green));
            rb.setChecked(true);
        }

    }
}
