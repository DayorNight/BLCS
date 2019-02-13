package blcs.lwb.lwbtool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import blcs.lwb.lwbtool.base.BaseFragment;
import blcs.lwb.lwbtool.base.BaseFragmentActivity;
import blcs.lwb.lwbtool.base.BasePresenter;
import interfaces.IAllFragment;
import interfaces.IPublicFragment;
import blcs.lwb.lwbtool.manager.FramentManages;
import blcs.lwb.lwbtool.presenter.PublicFragmentPresenter;


public abstract class PublicFragmentActivity extends BaseFragmentActivity implements IPublicFragment ,IAllFragment{

    public Toolbar tlToolbar;
    public FramentManages fm;

    /**
     * 启动这个Activity的Intent 带参数
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
        fm = FramentManages.getInstance(this,this);
        tlToolbar = findViewById(R.id.tl_toolbar);
        tlToolbar.setNavigationIcon(R.mipmap.ic_back_white);
        tlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回键
                // 如果剩下 最后一个片段就销毁Activity,否则执行后退栈
                if (fm.getAllFrament().size() <= 1) {
                    finish();
                } else {
                    fm.popBackStack();
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
            fm.addFrament(R.id.fr_contain, this, towhere, bundle, true);
        } else {
            this.finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (BaseFragment baseFragment : fm.getAllFrament()) {
            baseFragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
