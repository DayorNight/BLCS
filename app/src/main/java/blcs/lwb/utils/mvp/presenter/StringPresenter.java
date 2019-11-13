package blcs.lwb.utils.mvp.presenter;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.mvp.view.IStringView;

public class StringPresenter extends BasePresenter<IStringView> {

    private IStringView v;

    public StringPresenter(IStringView v) {
        this.v = v;
        init();
    }

    private void init() {
        v.initUI();
    }
}
