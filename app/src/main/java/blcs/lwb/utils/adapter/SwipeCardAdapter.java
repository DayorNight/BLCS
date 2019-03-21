package blcs.lwb.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import blcs.lwb.utils.bean.SwipeCardBean;
import blcs.lwb.utils.R;

/**
 * Created by ls on 2017/11/25.
 */

public class SwipeCardAdapter extends RecyclerView.Adapter<SwipeCardAdapter.SwipeCardViewHolder> {
    public ArrayList<SwipeCardBean> mData;
    public Context context;

    public SwipeCardAdapter(ArrayList<SwipeCardBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public SwipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_swipecard, null);
        SwipeCardViewHolder holder = new SwipeCardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SwipeCardViewHolder holder, int position) {
        SwipeCardViewHolder holder1=holder;
        holder1.recy_item_im.setBackgroundResource(mData.get(position).resoutimage);
        holder1.recy_item_tv.setText(mData.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class SwipeCardViewHolder extends RecyclerView.ViewHolder {
         TextView recy_item_tv;
         ImageView recy_item_im;

        public SwipeCardViewHolder(View itemView) {
            super(itemView);
            recy_item_im = (ImageView) itemView.findViewById(R.id.recy_item_im);
            recy_item_tv = (TextView) itemView.findViewById(R.id.recy_item_tv);
        }
    }
}