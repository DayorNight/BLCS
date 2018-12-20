package interfaces;

import android.app.Activity;
import blcs.lwb.lwbtool.base.BaseFragment;
public interface IAllFragment {
    BaseFragment getFragment(Activity activity, String alias);
}
