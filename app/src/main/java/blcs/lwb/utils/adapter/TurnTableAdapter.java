package blcs.lwb.utils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.List;

import blcs.lwb.lwbtool.base.BaseAdapter;
import blcs.lwb.utils.R;

public class TurnTableAdapter extends BaseAdapter<TurnTableAdapter.TurnTableViewHolder>{

    private Context context;
    private List<String> datas;

    public TurnTableAdapter(Context context, List<String> data) {
        super(context, data);
        this.context = context;
        this.datas = data;
    }

    @NonNull
    @Override
    public TurnTableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_turntable, viewGroup, false);
        return new TurnTableViewHolder(inflate);
    }
    private SparseBooleanArray mCheckStates = new SparseBooleanArray();
    @Override
    public void onBindViewHolder(@NonNull final TurnTableViewHolder holder, final int pos) {
        holder.cb.setText(datas.get(pos));
        holder.cb.setTag(pos);
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        holder.cb.setChecked(mCheckStates.get(pos, false));
    }

    class TurnTableViewHolder extends RecyclerView.ViewHolder{
        CheckBox cb;
        public TurnTableViewHolder(@NonNull View v) {
            super(v);
            cb= v.findViewById(R.id.cb_turntable);
        }
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
