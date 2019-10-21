package blcs.lwb.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.mvp.BaseFragmentActivity;
import blcs.lwb.utils.mvp.presenter.PublicFragmentPresenter;
import blcs.lwb.utils.mvp.view.IPublicFragment;
import butterknife.BindView;


public class PublicFragmentActivity extends BaseFragmentActivity implements IPublicFragment {

    @BindView(R.id.tl_toolbar)
    public Toolbar tlToolbar;


    /**
     * 启动这个Activity的Intent 带参数
     *
     * @param context
     * @return
     */
    public static Intent createIntent(Activity context, String arg, Bundle bundle) {
        return new Intent(context, PublicFragmentActivity.class).putExtra(Constants.Intent_Go, arg).putExtras(bundle);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_public_fragment;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return new PublicFragmentPresenter(this);
    }

    @Override
    public void Toolbar_init() {
        tlToolbar.setNavigationIcon(R.mipmap.ic_back_white);
        tlToolbar.inflateMenu(R.menu.menu_multi_language);
        tlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回键
                // 如果剩下 最后一个片段就销毁Activity,否则执行后退栈
                if (fragmentManager.getAllFrament().size() <= 1) {
                    finish();
                } else {
                    fragmentManager.popBackStack();
                }
            }
        });
    }


    @Override
    public void Show_Fragment() {
        Intent intent = getIntent();
        String towhere = intent.getStringExtra(Constants.Intent_Go);
        if (towhere != null) {
            Bundle bundle = intent.getExtras();
            fragmentManager.addFrament(R.id.fr_contain, this, towhere, bundle, false);
        } else {
            this.finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (BaseFragment baseFragment : fragmentManager.getAllFrament()) {
            baseFragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
