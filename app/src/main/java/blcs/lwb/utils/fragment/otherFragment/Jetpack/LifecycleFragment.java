package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;

public class LifecycleFragment extends BaseFragment {
    @Override
    protected int bindLayout() {
        return R.layout.fragment_lifecycle;
    }

    @Override
    protected void initView() {
        getLifecycle().addObserver(new MyObserver());
        
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

   class MyObserver implements LifecycleObserver{
       private static final String TAG = "MyObserver";
       @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
       public void create(){
           Log.e(TAG, "create: " );
       }
       @OnLifecycleEvent(Lifecycle.Event.ON_START)
       public void start(){
           Log.e(TAG, "start: " );
       }
       @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
       public void resume(){
           Log.e(TAG, "resume: " );
       }
       @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
       public void pause(){
           Log.e(TAG, "pause: " );
       }
       @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
       public void stop(){
           Log.e(TAG, "stop: " );
       }
       @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
       public void destroy(){
           Log.e(TAG, "destroy: " );
       }
       @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
       public void any(){
           Log.e(TAG, "any: " );
       }
   }
}


