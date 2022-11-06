package blcs.lwb.utils.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import butterknife.BindView;

/**
 * @author linweibin
 * @date 2021/12/30
 */
public abstract class BaseListFragment extends BaseFragment implements OnItemClickListener {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRv;
    private StringAdapter stringAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(activity));
        stringAdapter = new StringAdapter();
        mRv.setAdapter(stringAdapter);

        stringAdapter.setOnItemClickListener(this);
    }

    public void setListDatas(List<String> datas){
        stringAdapter.setNewData(datas);
        stringAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    public abstract void itemClick(BaseQuickAdapter adapter, View view, int position);

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        itemClick(adapter,view,position);
    }

    class StringAdapter extends BaseQuickAdapter<String, BaseViewHolder>{
        public StringAdapter() {
            super(R.layout.adapter_string);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.btn_string,item);
        }
    }
}
