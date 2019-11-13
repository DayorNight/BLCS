package blcs.lwb.utils.fragment.viewFragment;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.MyRenderer;
import blcs.lwb.lwbtool.utils.OpenGLUitl;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class OpenGlTriangleFragment extends BaseFragment {
    @BindView(R.id.gl_surface_view)
    GLSurfaceView glSurfaceView;
    private boolean rendererSet=false;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        if (OpenGLUitl.supportGLEs2(activity)) {
            glSurfaceView.setEGLContextClientVersion(2);
            MyRenderer myRenderer = new MyRenderer(activity);
            glSurfaceView.setRenderer(myRenderer);
            rendererSet = true;
        } else {
            Toast.makeText(activity, R.string.No_SupportOpenGL2, Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_opengl_triangle;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
