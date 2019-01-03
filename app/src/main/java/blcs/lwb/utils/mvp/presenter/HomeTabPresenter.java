package blcs.lwb.utils.mvp.presenter;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.mvp.view.IHomeTabView;

public class HomeTabPresenter extends BasePresenter<IHomeTabView> {

    private IHomeTabView v;
    public HomeTabPresenter(IHomeTabView v){
        this.v = v;
        onAttach(v);
    }

    public void init() {
        v.Recycler_init();
        v.Recycler_click();
    }
}
