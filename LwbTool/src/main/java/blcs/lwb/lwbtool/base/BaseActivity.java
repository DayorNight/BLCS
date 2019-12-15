/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package blcs.lwb.lwbtool.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import blcs.lwb.lwbtool.manager.AppManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO 基础Activity，通过继承可获取或使用 里面创建的 组件 和 方法;不能用于FragmentActivity
 *
 * @author Lemon
 * @use extends BaseActivity, 具体参考.DemoActivity
 * /滑动返回/显示与关闭进度弹窗方法/启动新Activity方法/快捷显示short toast方法/线程名列表/点击返回键事件/
 */
public abstract class BaseActivity<T extends BaseView, P extends BasePresenter<T>> extends Activity implements BaseView {
    private static final String TAG = "BaseActivity";//用于打印日志（log）的类的标记
    protected BaseActivity context = null;
    protected View view = null;
    private Unbinder bind;
    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(bindLayout());
        bind = ButterKnife.bind(this);
        presenter = bindPresenter();
        if (presenter != null) {
            presenter.onAttach((T) this);
        }
        initView();
    }

    /**
     * 绑定P层
     */
    protected abstract P bindPresenter();

    /**
     * TODO UI显示方法，必须在子类onCreate方法内setContentView后调用
     */
    public abstract void initView();

    /**
     * TODO 关联布局
     */
    public abstract int bindLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetch();
            presenter = null;
        }
        bind.unbind();
        AppManager.getAppManager().finishActivity(this);
    }
}