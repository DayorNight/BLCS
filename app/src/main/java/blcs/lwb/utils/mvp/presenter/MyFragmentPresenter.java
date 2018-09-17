package blcs.lwb.utils.mvp.presenter;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.mvp.view.IMyFragment;

public class MyFragmentPresenter extends BasePresenter<IMyFragment> {

    private IMyFragment v;
    public MyFragmentPresenter(IMyFragment v){
        this.v = v;
        init();
    }

    public void init() {
        v.Text();
    }
}
