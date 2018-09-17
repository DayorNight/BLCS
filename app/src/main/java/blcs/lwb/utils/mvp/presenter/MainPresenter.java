package blcs.lwb.utils.mvp.presenter;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.mvp.view.IMainView;

public class MainPresenter extends BasePresenter<IMainView> {

    private IMainView v;
    public MainPresenter(IMainView v){
        this.v = v;
        initMenu();
        initViewPage();
    }

    private void initViewPage() {

        v.ViewPage();
    }

    private void initMenu() {
        v.BottomMenu();
    }

}
