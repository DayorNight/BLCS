package blcs.lwb.lwbtool.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blcs.lwb.lwbtool.Constants;
import blcs.lwb.lwbtool.PublicFragmentActivity;
import blcs.lwb.lwbtool.R;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import interfaces.IPopBackStackListener;
import blcs.lwb.lwbtool.manager.FramentManages;

/**
 * BaseFrament
 */
public abstract class BaseFragment extends Fragment implements IPopBackStackListener, BaseView {
    private Bundle BackBundle;
    /**
     * 该fragment全局视图，不能在子Fragment中创建
     */
    private View mView ;

    /**
     * 可用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
     */
    private int returnCode = -1;
    protected PublicFragmentActivity activity;
    public FramentManages fragmentManager;
    private BasePresenter baseP;
    private Unbinder bind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(bindLayout(), container, false);
            mView.setClickable(true);
            activity = (PublicFragmentActivity) getActivity();// 获得activity的对象
            fragmentManager = activity.fm;//片段管理
            bind = ButterKnife.bind(this, mView);
            baseP = bindPresenter();
            if (baseP != null) {
                baseP.onAttach(this);
            }
            //每个Activity的中间标题必须都用这个id
            Bundle arguments = getArguments();
            if (arguments != null) {
                String title = (String) arguments.get(Constants.Item_Name);
                activity.tlToolbar.setTitle(title);
            }
            setMiddleTitle(activity.tlToolbar);//设置标题
            initView();
        }
        return mView;
    }

    /**
     * 中心的标题设置
     */
    public abstract void setMiddleTitle(android.support.v7.widget.Toolbar title);

    /**
     * UI
     * @return
     */
    protected abstract void initView();
    /**
     * 绑定P层
     *
     * @return
     */
    protected abstract BasePresenter bindPresenter();

    /**
     * 绑定界面
     *
     * @return
     */
    protected abstract int bindLayout();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fragmentManager.getLastFrament() != null) {
            fragmentManager.getLastFrament().popBackListener(returnCode, BackBundle);
            fragmentManager.getLastFrament().setMiddleTitle(activity.tlToolbar);
        }
        if (baseP != null) {
            baseP.onDetch();
        }
        bind.unbind();
    }

    /**
     * 调用这个方法实现后退，指定返回码和数据
     *
     * @param returnCode
     * @param bundle
     */
    public void popback(int returnCode, Bundle bundle) {
        this.returnCode = returnCode;
        this.BackBundle = bundle;
        int count = activity.fm.getAllFrament().size();
        if (count > 1) {
            fragmentManager.popBackStack();
        } else {
            fragmentManager.clearAllFrament();
            activity.finish();
        }
    }

    /**
     * 调用这个方法实现后退，默认返回returnCode=-1和bundle=null
     */
    public void popback() {
        popback(-1, null);
    }

    /**
     * 没有设置popback的值的话默认返回-1和null
     */
    @Override
    public void popBackListener() {
        // 没有设置popback的值的话默认返回-1和null
        popBackListener(returnCode, BackBundle);
    }

    /**
     * 添加一个片段
     * @param viewId 添加的控件位置
     * @param alias  别名
     * @param bundle 数据
     */
    public void addFrament(int viewId, String alias, Bundle bundle, boolean isAnim) {
        fragmentManager.addFrament(viewId, activity, alias, bundle, isAnim);
    }


    public void addFrament(String alias, Bundle bundle) {
        fragmentManager.addFrament(R.id.fr_contain, activity, alias, bundle, true);
    }
}