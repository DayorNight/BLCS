package blcs.lwb.lwbtool.presenter;

import blcs.lwb.lwbtool.base.BasePresenter;
import interfaces.IPublicFragment;

public class PublicFragmentPresenter extends BasePresenter<IPublicFragment> {

    private IPublicFragment v;
    public PublicFragmentPresenter(IPublicFragment v){
        this.v = v;
        init();
    }

    public void init() {
        v.Toolbar_init();
        v.Show_Fragment();
    }
}
