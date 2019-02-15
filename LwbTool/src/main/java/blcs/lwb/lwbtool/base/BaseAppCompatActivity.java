package blcs.lwb.lwbtool.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.manager.AppManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * AppCompatActivity基础封装
 */
public abstract class BaseAppCompatActivity<T extends BaseView, P extends BasePresenter<T>> extends AppCompatActivity implements BaseView {
    protected P presenter;// P层 自己在强转
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        AppManager.getAppManager().addActivity(this);
        bind = ButterKnife.bind(this);
        presenter = bindPresenter();
        if (presenter != null) {
            presenter.onAttach((T) this);
        }
    }

    /**
     * 绑定P层
     */
    protected abstract P bindPresenter();

    /**
     * 绑定布局
     */
    protected abstract int bindLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetch();
            presenter = null;
        }
        bind.unbind();
    }
}