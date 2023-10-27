package com.mogusatech.primitivetriangle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class GLRenderer implements 
    GLSurfaceView.Renderer {
    private FloatBuffer vertexBuffer;

    @Override
    public void onSurfaceCreated(GL10 gl10,EGLConfig eglConfig) {

        GLES.makeProgram();

        GLES20.glEnableVertexAttribArray(GLES.positionHandle);

        float[] vertexs={
            0.0f,1.0f,0.0f,
            0.0f,0.0f,0.0f,
            1.0f,1.0f,0.0f,
        };
        vertexBuffer=makeFloatBuffer(vertexs);    
    }

    @Override
    public void onSurfaceChanged(GL10 gl10,int w,int h) {

        GLES20.glViewport(0,0,w,h);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUniform4f(GLES.colorHandle,1.0f,0.0f,0.0f,1.0f);

        GLES20.glVertexAttribPointer(GLES.positionHandle,3,
            GLES20.GL_FLOAT,false,0,vertexBuffer);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,3);
    }

    private FloatBuffer makeFloatBuffer(float[] array) {
        FloatBuffer fb=ByteBuffer.allocateDirect(array.length*4).order(
            ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(array).position(0);
        return fb;
    }
}