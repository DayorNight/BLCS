package blcs.lwb.utils.fragment.otherFragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.FunctionIntroAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class FunciotnIntroFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        FunctionIntroAdapter mAdapter = new FunctionIntroAdapter();
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL,mAdapter,mRecyclerView);
//        RequestUtils.getVersionList(this, new MyObserver<List<VersionBean>>(activity) {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//            @Override
//            public void onSuccess(List<VersionBean> result) {
//                mAdapter.setNewData(result);
//            }
//
//            @Override
//            public void onFailure(Throwable e, String errorMsg) {
//
//            }
//        });

//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                List<VersionBean> data = adapter.getData();
//                LinCustomDialogFragment.init()
//                        .setType(LinCustomDialogFragment.TYPE_SURE)
//                        .setContent(data.get(position).getVersionIntroduce())
//                        .show(getFragmentManager());
//            }
//        });

    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
