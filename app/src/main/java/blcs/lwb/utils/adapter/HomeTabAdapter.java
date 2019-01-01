package blcs.lwb.utils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import blcs.lwb.lwbtool.base.BaseAdapter;
import blcs.lwb.utils.R;

public class HomeTabAdapter extends BaseAdapter<HomeTabAdapter.HomeTab1Holder>{

    public HomeTabAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public HomeTabAdapter.HomeTab1Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.textview, null);
        HomeTab1Holder myViewHolder = new HomeTab1Holder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTabAdapter.HomeTab1Holder holder, int position) {
        List<String> data = getData();
        holder.tvItemHomeName.setText((position+1)+"→"+data.get(position));
        super.bindOnClickListener(holder, position);
        super.bindOnLongClickListener(holder, position);
    }

    class HomeTab1Holder extends RecyclerView.ViewHolder {
        TextView tvItemHomeName;
        public HomeTab1Holder(View view) {
            super(view);
            tvItemHomeName = (TextView) view.findViewById(R.id.tv_item_home_name);
        }
    }
}
