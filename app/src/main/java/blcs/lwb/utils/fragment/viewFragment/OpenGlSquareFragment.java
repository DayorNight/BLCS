package blcs.lwb.utils.fragment.viewFragment;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.OpenGLUitl;
import blcs.lwb.lwbtool.utils.SquareRenderer;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class OpenGlSquareFragment extends BaseFragment {
    @BindView(R.id.gl_surface_square)
    GLSurfaceView gl_surface_square;
    private boolean rendererSet=false;
    private SquareRenderer square;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        if (OpenGLUitl.supportGLEs2(activity)) {
            gl_surface_square.setEGLContextClientVersion(2);
            square = new SquareRenderer(activity);
            gl_surface_square.setRenderer(square);
            gl_surface_square.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
            rendererSet = true;
        } else {
            Toast.makeText(activity, R.string.No_SupportOpenGL2, Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_opengl_square;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (rendererSet) {
            gl_surface_square.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rendererSet) {
            gl_surface_square.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(square!=null){
            square.destroy();
        }
    }
}
