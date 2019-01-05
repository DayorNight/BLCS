package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import blcs.lwb.lwbtool.R;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;


/**
 * Created by lwb on 2018/6/1.
 * OpenGL渲染器
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    private Context context;
    private int program;
    private static final String V_POSITION = "vPosition";
    private int aPositionLocation;
    private final FloatBuffer vertexData;
    private static final float[] VERTEX = {   // in counterclockwise order:
            0, 1, 0,  // top
            -1, 0, 0,  // bottom left
            1, 0, 0,  // bottom right
    };
    public MyRenderer(Context context) {
        this.context = context;
        //获取一块本地内存空间，存储大小= 数组长度*每个浮点数有4个字节，
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4);
        //设置内存空间字节排列顺序
        byteBuffer.order(ByteOrder.nativeOrder());
        //将字节缓冲区转换成浮点缓冲区
        vertexData = byteBuffer.asFloatBuffer();
        //存储数据
        vertexData.put(VERTEX);
        vertexData.position(0);
    }
    /**
     * 程序第一次运行或其他activity切换回来时
     * surface初始化
     * @param gl
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置清空屏幕用的颜色。
        GLES20.glClearColor(0.0f,0.0f,0.0f,0.0f);
        //获取着色器的代码
        String vertexShaderSource = OpenGLUitl.getRawResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = OpenGLUitl.getRawResource(context, R.raw.simple_fragment_shader);
        //加载着色器
        int vertexShader = OpenGLUitl.compileVertexShader(vertexShaderSource);
        int fragmentShader = OpenGLUitl.compileFragmentShader(fragmentShaderSource);
        //链接程序ID
        program = OpenGLUitl.linkShader(vertexShader, fragmentShader);
        //使用着色器
        glUseProgram(program);
        //获取着色器代码中的变量索引
        aPositionLocation = glGetAttribLocation(program, V_POSITION);
        //绑定启用Vertex坐标值
        glVertexAttribPointer(aPositionLocation,3,GL_FLOAT,false,12,vertexData);
        //启用Vertex
        glEnableVertexAttribArray(aPositionLocation);
    }

    /**
     * 每次surface尺寸变化时
     * @param gl
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置窗口大小
        gl.glViewport(0,0,width,height);
    }

    /**
     * 绘制图形
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //清空屏幕
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
