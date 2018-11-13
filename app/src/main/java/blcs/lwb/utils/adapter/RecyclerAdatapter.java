package blcs.lwb.utils.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import blcs.lwb.lwbtool.LogUtils;
import blcs.lwb.lwbtool.RxToast;
import blcs.lwb.lwbtool.StringUtils;
import blcs.lwb.lwbtool.base.BaseAdapter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.RecyclerViewFragment;

public class RecyclerAdatapter extends BaseAdapter<RecyclerAdatapter.RecyclerViewHolder> {
    private Activity context;
    private   List data;
    public RecyclerAdatapter(Activity context, List data) {
        super(context, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        final String title = (String) data.get(i);
        holder.tvAdapterItem.setText(i+"、"+title);
        holder.btnAdapterItem.setText(title);
        holder.btnAdapterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxToast.showToastShort(context,title);
            }
        });
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvAdapterItem;
        Button btnAdapterItem;
        public RecyclerViewHolder(@NonNull View v) {
            super(v);
            tvAdapterItem= (TextView)v.findViewById(R.id.tv_adapter_item);
            btnAdapterItem= (Button)v.findViewById(R.id.btn_adapter_item);

        }
    }
}
