package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import blcs.lwb.lwbtool.R;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by lwb on 2018/6/19.
 */

public class SquareRenderer implements GLSurfaceView.Renderer{
    private static final String V_POSITION = "vPosition";
    private static final String A_TexCoord = "a_texCoord";
    private static final String U_MVPMatrix = "uMVPMatrix";
    private static final String S_Texture = "s_texture";
    //顶点
    private static final float[] VERTEX = {   // in counterclockwise order:
            1, 1, 0,   // top right
            -1, 1, 0,  // top left
            -1, -1, 0, // bottom left
            1, -1, 0,  // bottom right
    };
    //顺序
    private static final short[] VERTEX_INDEX = {
            0, 1, 2, 0, 2, 3
    };

    //指定截取纹理的哪一部分绘制到图形上
    private static final float[] TEX_VERTEX = {   // in clockwise order:
            0.5f, 0,  // bottom right
            0, 0,  // bottom left
            0, 0.5f,  // top left
            0.5f, 0.5f,  // top right
    };

    private final FloatBuffer mVertexBuffer;
    private final FloatBuffer mTexVertexBuffer;
    private final ShortBuffer mVertexIndexBuffer;
    private final float[] mMVPMatrix = new float[16];

    private int mProgram;
    private int mPositionHandle;
    private int mMatrixHandle;
    private int mTexCoordHandle;
    private int mTexSamplerHandle;
    private int mTexName;
    private Context mContext;

    public SquareRenderer(Context context) {
        this.mContext = context;
        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX);
        mVertexBuffer.position(0);

        mVertexIndexBuffer = ByteBuffer.allocateDirect(VERTEX_INDEX.length * 2)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(VERTEX_INDEX);
        mVertexIndexBuffer.position(0);

        mTexVertexBuffer = ByteBuffer.allocateDirect(TEX_VERTEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(TEX_VERTEX);
        mTexVertexBuffer.position(0);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        //获取着色器的代码
        String vertexShaderSource = OpenGLUitl.getRawResource(mContext, R.raw.simple_square_vertex_shader);
        String fragmentShaderSource = OpenGLUitl.getRawResource(mContext, R.raw.simple_square_fragment_shader);
        //加载着色器
        int vertexShader = OpenGLUitl.compileVertexShader(vertexShaderSource);
        int fragmentShader = OpenGLUitl.compileFragmentShader(fragmentShaderSource);
        //链接程序ID
        mProgram = OpenGLUitl.linkShader(vertexShader, fragmentShader);
        //使用着色器
        glUseProgram(mProgram);
        //获取顶点着色器代码中的变量索引
        mPositionHandle = GLES20.glGetAttribLocation(mProgram,V_POSITION);
        //获取颜色着色器
        mTexCoordHandle = GLES20.glGetAttribLocation(mProgram, A_TexCoord);
        mMatrixHandle = GLES20.glGetUniformLocation(mProgram, U_MVPMatrix);
        mTexSamplerHandle = GLES20.glGetUniformLocation(mProgram, S_Texture);
        //启用Vertex
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //绑定启用Vertex坐标值
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false,
                12, mVertexBuffer);
        //启用Vertex
        GLES20.glEnableVertexAttribArray(mTexCoordHandle);
        GLES20.glVertexAttribPointer(mTexCoordHandle, 2, GLES20.GL_FLOAT, false, 0,
                mTexVertexBuffer);

        int[] texNames = new int[1];
        // 创建纹理
        GLES20.glGenTextures(1, texNames, 0);
        mTexName = texNames[0];
        //加载图片
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.mipmap.ic_launcher);
        //激活指定编号的纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        //将新建的纹理和编号绑定起来
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexName);
        //对图片纹理设置一系列参数
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_REPEAT);
        //图片数据拷贝到纹理
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        Matrix.perspectiveM(mMVPMatrix, 0, 45, (float) width / height, 0.1f, 100f);
        Matrix.translateM(mMVPMatrix, 0, 0f, 0f, -15f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUniformMatrix4fv(mMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glUniform1i(mTexSamplerHandle, 0);
        //用glDrawElements 来绘制，mVertexIndexBuffer 指定了顶点绘制顺序
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, VERTEX_INDEX.length,
                GLES20.GL_UNSIGNED_SHORT, mVertexIndexBuffer);
    }

    public void destroy() {
        GLES20.glDeleteTextures(1, new int[] { mTexName }, 0);
    }
}
