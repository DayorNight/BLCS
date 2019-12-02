package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LifecycleFragment extends BaseFragment {
    @BindView(R.id.tv_lifecycle)
    TextView tvLifecycle;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_lifecycle;
    }

    @Override
    protected void initView() {
        MyObserver myObserver = new MyObserver(this);
        getLifecycle().addObserver(myObserver);
        BroadCastObserver broadCastObserver = new BroadCastObserver(activity);
        getLifecycle().addObserver(broadCastObserver);

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
    @OnClick({R.id.btn_send,R.id.btn_lifeCycle})
    public void send(View view){
        switch (view.getId()){
            case R.id.btn_send:
                Intent intent = new Intent();
                intent.setAction("xxx");
                intent.putExtra("content", "广播1号");
                activity.sendBroadcast(intent);
                break;
            case R.id.btn_lifeCycle:
                MyUtils.toUrl(this,"LifeCycle",getString(R.string.URL_Lifecycle1));
                break;
        }
    }

    class MyObserver implements LifecycleObserver {
        private static final String TAG = "MyObserver";
        private LifecycleFragment lifecycleFragment;
        private final StringBuilder stringBuilder;

        public MyObserver(LifecycleFragment lifecycleFragment) {
            this.lifecycleFragment = lifecycleFragment;
            stringBuilder = new StringBuilder();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void create() {
            stringBuilder.append(TAG+"===create: \n");
            print();
            Log.e(TAG, "create: ");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void start() {
            stringBuilder.append(TAG+"===start: \n");
            print();
            Log.e(TAG, "start: ");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void resume() {
            stringBuilder.append(TAG+"===resume: \n");
            print();
            Log.e(TAG, "resume: ");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void pause() {
            stringBuilder.append(TAG+"===pause: \n");
            print();
            Log.e(TAG, "pause: ");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void stop() {
            stringBuilder.append(TAG+"===stop: \n");
            print();
            Log.e(TAG, "stop: ");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void destroy() {
            stringBuilder.append(TAG+"===destroy: \n");
            print();
            Log.e(TAG, "destroy: ");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void any() {
            stringBuilder.append(TAG+"===any: \n");
            print();
            Log.e(TAG, "any: ");
        }
        private void print() {
            lifecycleFragment.tvLifecycle.setText(stringBuilder.toString());
        }
    }
    //广播接受者
    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("content"),Toast.LENGTH_SHORT).show();
            Log.e("onReceive: action ",intent.getAction());
            Log.e("onReceive: content ",intent.getStringExtra("content"));
        }
    }
    //广播观察者
    class BroadCastObserver implements LifecycleObserver {
        private static final String TAG = "BroadCastObserver";
        private Activity mActivity;
        private MyBroadcastReceiver mBroadcastReceiver;
        public BroadCastObserver(Activity activity) {
            this.mActivity = activity;
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void register(){
            Log.e(TAG, "register: " );
            mBroadcastReceiver = new MyBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("xxx");
            mActivity.registerReceiver(mBroadcastReceiver, intentFilter);
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void unRegister(){
            Log.e(TAG, "unRegister: " );
            mActivity.unregisterReceiver(mBroadcastReceiver);
        }
    }
}


