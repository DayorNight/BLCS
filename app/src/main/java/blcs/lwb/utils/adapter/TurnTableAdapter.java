package blcs.lwb.utils.adapter;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;
import blcs.lwb.utils.R;

public class TurnTableAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private final SparseBooleanArray mCheckStates = new SparseBooleanArray();
    public TurnTableAdapter( List<String> data) {
        super(R.layout.adapter_turntable, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final int pos = helper.getAdapterPosition();
        helper.setText(R.id.cb_turntable,item);
        CheckBox view = helper.getView(R.id.cb_turntable);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckStates.put(pos, true);
                } else {
                    mCheckStates.delete(pos);
                }
                onItemClickListener.onclick(buttonView,pos,isChecked);
            }
        });
        view.setChecked(mCheckStates.get(helper.getAdapterPosition(), false));
    }


    public OnItemClickListener onItemClickListener;
    /**
     * 点击事件
     */
    public void setOnItemClick(OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public interface OnItemClickListener{
        void onclick(CompoundButton box,int pos,boolean isChecked);
    }
}
