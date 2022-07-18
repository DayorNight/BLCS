package blcs.lwb.utils.utils;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import blcs.lwb.utils.R;

public class DemoViewModel extends AndroidViewModel {
    public String tip = "ViewModel生命周期";
    public DemoViewModel(Application application) {
        super(application);
    }

    public String useViewModel(){
        return getApplication().getString(R.string.viewModel);
    }

}
